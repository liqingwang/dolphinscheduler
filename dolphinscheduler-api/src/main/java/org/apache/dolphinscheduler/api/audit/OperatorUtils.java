package org.apache.dolphinscheduler.api.audit;

import org.apache.dolphinscheduler.api.audit.enums.AuditType;
import org.apache.dolphinscheduler.api.enums.ExecuteType;
import org.apache.dolphinscheduler.api.utils.Result;
import org.apache.dolphinscheduler.common.enums.AuditOperationType;
import org.apache.dolphinscheduler.common.enums.ReleaseState;
import org.apache.dolphinscheduler.dao.entity.AuditLog;
import org.apache.dolphinscheduler.dao.entity.User;
import org.apache.dolphinscheduler.spi.enums.ResourceType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

@Slf4j
public class OperatorUtils {

    protected void changeObjectForVersionRelated(AuditOperationType auditOperationType, Map<String, Object> paramsMap,
                                                 List<AuditLog> auditLogList) {
        switch (auditOperationType) {
            case SWITCH_VERSION:
            case DELETE_VERSION:
                auditLogList.get(0).setObjectName(paramsMap.get("version").toString());
                break;
            default:
                break;
        }
    }

    public static boolean resultFail(Result result) {
        if (result != null && result.isFailed()) {
            return true;
        }

        return false;
    }

    public static List<AuditLog> buildAuditLogList(String apiDescription, AuditType auditType, User user) {
        List<AuditLog> auditLogList = new ArrayList<>();
        AuditLog auditLog = new AuditLog();
        auditLog.setUserId(user.getId());
        auditLog.setObjectType(auditType.getAuditObjectType().getName());
        auditLog.setOperationType(auditType.getAuditOperationType().getName());
        auditLog.setDescription(apiDescription);
        auditLog.setTime(new Date());
        auditLogList.add(auditLog);

        return auditLogList;
    }

    public static User getUser(Map<String, Object> paramsMap) {
        for (Object object : paramsMap.values()) {
            if (object instanceof User) {
                return (User) object;
            }
        }

        return null;
    }

    public static Map<String, Object> getParamsMap(ProceedingJoinPoint point, MethodSignature signature) {
        Object[] args = point.getArgs();
        String[] strings = signature.getParameterNames();

        Map<String, Object> paramsMap = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            paramsMap.put(strings[i], args[i]);
        }

        return paramsMap;
    }

    public static AuditOperationType modifyReleaseOperationType(AuditType auditType, Map<String, Object> paramsMap) {
        switch (auditType.getAuditOperationType()) {
            case RELEASE:
                ReleaseState releaseState = (ReleaseState) paramsMap.get("releaseState");
                if (releaseState == null) {
                    break;
                }
                switch (releaseState) {
                    case ONLINE:
                        return AuditOperationType.ONLINE;
                    case OFFLINE:
                        return AuditOperationType.OFFLINE;
                    default:
                        break;
                }
                break;
            case EXECUTE:
                ExecuteType executeType = (ExecuteType) paramsMap.get("executeType");
                if (executeType == null) {
                    break;
                }
                switch (executeType) {
                    case REPEAT_RUNNING:
                        return AuditOperationType.RERUN;
                    case RECOVER_SUSPENDED_PROCESS:
                        return AuditOperationType.RESUME_PAUSE;
                    case START_FAILURE_TASK_PROCESS:
                        return AuditOperationType.RESUME_FAILURE;
                    case STOP:
                        return AuditOperationType.STOP;
                    case PAUSE:
                        return AuditOperationType.PAUSE;
                    case EXECUTE_TASK:
                        return AuditOperationType.EXECUTE;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        return auditType.getAuditOperationType();
    }

    public static long getObjectIdentityByParma(String[] paramNameArr, Map<String, Object> paramsMap) {
        for (String name : paramNameArr) {
            if (paramsMap.get(name) instanceof String) {
                String param = (String) paramsMap.get(name);
                try {
                    if (param.matches("\\d+")) {
                        return Long.parseLong(param);
                    }
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
        }

        return -1;
    }

    public static Map<String, Object> getObjectIfFromReturnObject(Object obj, String[] params) {
        Map<String, Object> map = new HashMap<>();

        try {
            Class<?> clazz = obj.getClass();

            if (clazz.equals(Long.class)) {
                map.put(params[0], obj);
            }

            while (clazz != null) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);

                    if (field.getName().equals(params[0])) {
                        map.put(params[0], field.get(obj));
                    }
                }

                clazz = clazz.getSuperclass();
            }
        } catch (Exception e) {
            log.error("get object if from return object error", e);
        }

        return map;
    }

    public static boolean isUdfResource(Map<String, Object> paramsMap) {
        ResourceType resourceType = (ResourceType) paramsMap.get("type");
        return resourceType != null && resourceType.equals(ResourceType.UDF);
    }

}
