/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler.api.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.dolphinscheduler.api.dto.AuditDto;
import org.apache.dolphinscheduler.api.service.AuditService;
import org.apache.dolphinscheduler.api.utils.PageInfo;
import org.apache.dolphinscheduler.common.enums.AuditObjectType;
import org.apache.dolphinscheduler.common.enums.AuditOperationType;
import org.apache.dolphinscheduler.dao.entity.*;
import org.apache.dolphinscheduler.dao.mapper.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.parquet.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Service
public class AuditServiceImpl extends BaseServiceImpl implements AuditService {

    @Autowired
    private AuditLogMapper auditLogMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProcessDefinitionMapper processDefinitionMapper;

    @Autowired
    private ProcessInstanceMapper processInstanceMapper;

    @Autowired
    private TaskDefinitionMapper taskDefinitionMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private UdfFuncMapper udfFuncMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private AlertGroupMapper alertGroupMapper;

    @Autowired
    private AlertPluginInstanceMapper alertPluginInstanceMapper;

    @Autowired
    private WorkerGroupMapper workerGroupMapper;

    @Autowired
    private QueueMapper queueMapper;

    @Autowired
    private EnvironmentMapper environmentMapper;

    @Autowired
    private ClusterMapper clusterMapper;

    @Autowired
    private K8sNamespaceMapper k8sNamespaceMapper;

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    @Override
    public void addAudit(AuditLog auditLog) {
        auditLogMapper.insert(auditLog);
    }

    @Override
    public void addAudit(List<AuditLog> auditLogList, long duration) {
        auditLogList.forEach(auditLog -> {
            auditLog.setDuration(duration);
            addAudit(auditLog);
        });
    }

    @Override
    public void addQuartzLog(int processId) {
        AuditLog auditLog = new AuditLog();
        auditLog.setObjectId((long)processId);
        auditLog.setObjectType(AuditObjectType.PROCESS.getCode());
        auditLog.setOperationType(AuditOperationType.RUN.getCode());
        auditLog.setTime(new Date());
        auditLog.setUserId(-1);
        auditLogMapper.insert(auditLog);
    }

    /**
     * query audit log paging
     *
     * @param loginUser           login user
     * @param objectTypeCodes     object type codes
     * @param operationTypeCodes  operation type codes
     * @param startDate           start time
     * @param endDate             end time
     * @param userName            query user name
     * @param pageNo              page number
     * @param pageSize            page size
     * @return audit log string data
     */
    @Override
    public PageInfo<AuditDto> queryLogListPaging(User loginUser,
                                                 String objectTypeCodes,
                                                 String operationTypeCodes,
                                                 String startDate,
                                                 String endDate,
                                                 String userName,
                                                 Integer pageNo,
                                                 Integer pageSize) {

        List<Integer> objectTypeCodeList = convertStringToIntList(objectTypeCodes);
        List<Integer> operationTypeCodeList = convertStringToIntList(operationTypeCodes);

        Date start = checkAndParseDateParameters(startDate);
        Date end = checkAndParseDateParameters(endDate);

        IPage<AuditLog> logIPage = auditLogMapper.queryAuditLog(new Page<>(pageNo, pageSize), objectTypeCodeList, operationTypeCodeList,
                userName, start, end);
        List<AuditDto> auditDtos =
                logIPage.getRecords().stream().map(this::transformAuditLog).collect(Collectors.toList());

        PageInfo<AuditDto> pageInfo = new PageInfo<>(pageNo, pageSize);
        pageInfo.setTotal((int) logIPage.getTotal());
        pageInfo.setTotalList(auditDtos);
        return pageInfo;
    }

    private List<Integer> convertStringToIntList(String codes) {
        if(Strings.isNullOrEmpty(codes)) {
            return new ArrayList<>();
        }

        return Arrays.stream(codes.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    /**
     * transform AuditLog to AuditDto
     *
     * @param auditLog audit log
     * @return audit dto
     */
    private AuditDto transformAuditLog(AuditLog auditLog) {
        AuditDto auditDto = new AuditDto();
        AuditObjectType objectType = AuditObjectType.of(auditLog.getObjectType());
        auditDto.setObjectType(objectType.getName());
        auditDto.setObjectName(auditLog.getObjectName());
        auditDto.setOperation(AuditOperationType.of(auditLog.getOperationType()).getName());
        auditDto.setUserName(auditLog.getUserName());
        auditDto.setDuration(auditLog.getDuration());
        auditDto.setDetail(auditLog.getDetail());
        auditDto.setDescription(auditLog.getDescription());
        auditDto.setTime(auditLog.getTime());
        return auditDto;
    }

    @Override
    public String getObjectNameByObjectId(Long objectId, AuditObjectType objectType) {
        switch (objectType) {
            case PROCESS_INSTANCE: {
                ProcessInstance obj = processInstanceMapper.queryDetailById(objectId.intValue());
                return obj == null ? "" : obj.getName();
            }
            case UDP_FUNCTION: {
                UdfFunc obj = udfFuncMapper.selectUdfById(objectId.intValue());
                return obj == null ? "" :obj.getFuncName();
            }
            case DATASOURCE: {
                DataSource obj = dataSourceMapper.selectById(objectId);
                return obj == null ? "" :obj.getName();
            }
            case TENANT: {
                Tenant obj = tenantMapper.selectById(objectId);
                return obj == null ? "" :obj.getTenantCode();
            }
            case USER: {
                User obj = userMapper.selectById(objectId);
                return obj == null ? "" :obj.getUserName();
            }
            case ALARM_GROUP: {
                AlertGroup obj = alertGroupMapper.selectById(objectId);
                return obj == null ? "" :obj.getGroupName();
            }
            case ALARM_INSTANCE: {
                AlertPluginInstance obj = alertPluginInstanceMapper.selectById(objectId);
                return obj == null ? "" :obj.getInstanceName();
            }
            case WORKER_GROUP: {
                WorkerGroup obj = workerGroupMapper.selectById(objectId);
                return obj == null ? "" :obj.getName();
            }
            case YARN_QUEUE: {
                Queue obj = queueMapper.selectById(objectId);
                return obj == null ? "" :obj.getQueueName();
            }
            case K8S_NAMESPACE: {
                K8sNamespace obj = k8sNamespaceMapper.selectById(objectId);
                return obj == null ? "" :obj.getNamespace();
            }
            case TOKEN: {
                AccessToken obj = accessTokenMapper.selectById(objectId);
                if (obj == null) return "";
                User user = userMapper.selectById(obj.getUserId());
                return user == null ? "" :user.getUserName();
            }
            case PROJECT: {
                Project obj = projectMapper.queryByCode(objectId);
                return obj == null ? "" :obj.getName();
            }
            case PROCESS: {
                ProcessDefinition obj = processDefinitionMapper.queryByCode(objectId);
                return obj == null ? "" :obj.getName();
            }
            case TASK: {
                TaskDefinition obj = taskDefinitionMapper.queryByCode(objectId);
                return obj == null ? "" :obj.getName();
            }
            case ENVIRONMENT: {
                Environment obj = environmentMapper.queryByEnvironmentCode(objectId);
                return obj == null ? "" :obj.getName();
            }
            case CLUSTER: {
                Cluster obj = clusterMapper.queryByClusterCode(objectId);
                return obj == null ? "" :obj.getName();
            }
            default:
                return "";
        }
    }
}
