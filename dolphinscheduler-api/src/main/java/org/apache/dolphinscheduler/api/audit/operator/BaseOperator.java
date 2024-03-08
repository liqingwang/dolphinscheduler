package org.apache.dolphinscheduler.api.audit.operator;

import org.apache.dolphinscheduler.api.audit.OperatorUtils;
import org.apache.dolphinscheduler.api.audit.enums.AuditType;
import org.apache.dolphinscheduler.api.service.AuditService;
import org.apache.dolphinscheduler.api.utils.Result;
import org.apache.dolphinscheduler.dao.entity.AuditLog;
import org.apache.dolphinscheduler.dao.entity.User;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

@Service
@Slf4j
public abstract class BaseOperator implements Operator {

    @Autowired
    private AuditService auditService;

    public Object recordAudit(ProceedingJoinPoint point, String describe, AuditType auditType) throws Throwable {
        long beginTime = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Map<String, Object> paramsMap = OperatorUtils.getParamsMap(point, signature);

        User user = OperatorUtils.getUser(paramsMap);

        if (user == null) {
            log.error("user is null");
            return point.proceed();
        }

        List<AuditLog> auditLogList = OperatorUtils.buildAuditLogList(describe, auditType, user);
        setRequestParam(auditType, auditLogList, paramsMap);

        Result result = (Result) point.proceed();
        if (OperatorUtils.resultFail(result)) {
            log.error("request fail, code {}", result.getCode());
            return result;
        }

        setObjectIdentityFromReturnObject(auditType, result, auditLogList);

        modifyAuditOperationType(auditType, paramsMap, auditLogList);
        modifyAuditObjectType(auditType, paramsMap, auditLogList);

        long latency = System.currentTimeMillis() - beginTime;
        auditService.addAudit(auditLogList, latency);

        return result;
    }

    protected void setRequestParam(AuditType auditType, List<AuditLog> auditLogList, Map<String, Object> paramsMap) {
        String[] paramNameArr = auditType.getRequestParamName();

        if (paramNameArr.length == 0) {
            return;
        }

        modifyRequestParams(paramNameArr, paramsMap, auditLogList);
        setObjectByParma(paramNameArr, paramsMap, auditLogList);

        if (auditLogList.get(0).getObjectId() == null) {
            auditLogList.get(0).setObjectId(OperatorUtils.getObjectIdentityByParma(paramNameArr, paramsMap));
        }
    }

    protected void setObjectByParma(String[] paramNameArr, Map<String, Object> paramsMap,
                                    List<AuditLog> auditLogList) {

        String name = paramNameArr[0];
        Object value = paramsMap.get(name);

        if (value == null) {
            return;
        }

        String objName = getObjectNameFromReturnIdentity(value);

        if (Strings.isNullOrEmpty(objName)) {
            auditLogList.get(0).setObjectName(value.toString());
            return;
        }

        auditLogList.get(0).setObjectId(Long.parseLong(value.toString()));
        auditLogList.get(0).setObjectName(objName);
    }

    protected void setObjectByParmaArr(String[] paramNameArr, Map<String, Object> paramsMap,
                                       List<AuditLog> auditLogList) {

        AuditLog auditLog = auditLogList.get(0);
        for (String param : paramNameArr) {
            if (!paramsMap.containsKey(param)) {
                continue;
            }

            String[] identityArr = ((String) paramsMap.get(param)).split(",");
            for (String identityString : identityArr) {
                long identity;
                try {
                    identity = Long.parseLong(identityString);
                } catch (Exception e) {
                    log.error("code is not long, code: {}", identityString);
                    continue;
                }

                String value = getObjectNameFromReturnIdentity(identity);

                if (value == null) {
                    continue;
                }

                auditLog.setObjectId(identity);
                auditLog.setObjectName(value);
                auditLogList.add(auditLog);
                auditLog = AuditLog.copyNewOne(auditLog);
            }
        }
        auditLogList.remove(0);
    }

    protected void setObjectIdentityFromReturnObject(AuditType auditType, Result result,
                                                     List<AuditLog> auditLogList) {
        String[] returnObjectFieldNameArr = auditType.getReturnObjectFieldName();
        if (returnObjectFieldNameArr.length == 0) {
            return;
        }
        Map<String, Object> returnObjectMap =
                OperatorUtils.getObjectIfFromReturnObject(result.getData(), returnObjectFieldNameArr);
        modifyObjectFromReturnObject(returnObjectFieldNameArr, returnObjectMap, auditLogList);
        setObjectNameFromReturnIdentity(auditLogList);
    }

    protected void setObjectNameFromReturnIdentity(List<AuditLog> auditLogList) {
        auditLogList
                .forEach(auditLog -> auditLog.setObjectName(getObjectNameFromReturnIdentity(auditLog.getObjectId())));
    }

    protected String getObjectNameFromReturnIdentity(Object identity) {
        return "";
    }

    protected void modifyRequestParams(String[] paramNameArr, Map<String, Object> paramsMap,
                                       List<AuditLog> auditLogList) {
    }

    protected void modifyAuditObjectType(AuditType auditType, Map<String, Object> paramsMap,
                                         List<AuditLog> auditLogList) {

    }

    protected void modifyAuditOperationType(AuditType auditType, Map<String, Object> paramsMap,
                                            List<AuditLog> auditLogList) {

    }

    protected void modifyObjectFromReturnObject(String[] params, Map<String, Object> returnObjectMap,
                                                List<AuditLog> auditLogList) {
        if (returnObjectMap.isEmpty()) {
            return;
        }

        auditLogList
                .forEach(auditLog -> auditLog.setObjectId(Long.parseLong(returnObjectMap.get(params[0]).toString())));
    }
}
