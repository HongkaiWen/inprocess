package audit.aspect;

import audit.service.AuditService;
import audit.vo.AuditLog;
import com.mongodb.BasicDBObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class AuditingAspect {

    @Autowired
    private AuditService auditService;

    private Logger logger = LoggerFactory.getLogger(AuditingAspect.class);

    @After("@annotation(auditable)")
    public void logAuditActivity(JoinPoint joinPoint, Auditable auditable) {
        AuditLog auditLog = null;
//        String account = SecurityContextHolder.getContext().getAuthentication().getName();
        String account = "";
        try{
            String action = auditable.action();
            String auditTable = auditable.auditTable();
            String bizRefNo = getBizRefNoViaAnnotation(joinPoint);

            String logMsg = getActionMsg(joinPoint.getArgs());
            auditLog = new AuditLog(account, new Date(), bizRefNo, action, logMsg);

            auditService.addAuditLog(auditTable, auditLog);
        } catch (Exception e){
            if(auditLog != null){
                logger.error(String.format("add audit log failed: %s", auditLog), e);
            } else {
                logger.error(String.format("add audit log failed user: %s, action: %s",
                        account, auditable.action()), e);
            }

        }
    }

    private String getBizRefNoViaAnnotation(JoinPoint joinPoint) throws InvocationTargetException, IllegalAccessException {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Annotation[][] parametersAnnotations = signature.getMethod().getParameterAnnotations();
        for(int i=0; i<parametersAnnotations.length; i++){
            Annotation[] parameterAnnotations = parametersAnnotations[i];
            for(Annotation annotation : parameterAnnotations){
                if(annotation.annotationType() == AuditBizRefNo.class){
                    AuditBizRefNo auditBizRefNo = (AuditBizRefNo) annotation;
                    if(StringUtils.isNotEmpty(auditBizRefNo.fieldInBasicDBObject())){
                        return getFieldValueFromBasicDBObject(joinPoint.getArgs()[i],
                                auditBizRefNo.fieldInBasicDBObject());
                    } else if(StringUtils.isNotEmpty(auditBizRefNo.fieldInObject())){
                        return getFieldValueFromObject(joinPoint.getArgs()[i],
                                auditBizRefNo.fieldInObject());
                    }
                    return joinPoint.getArgs()[i].toString();
                }
            }
        }
        return null;
    }

    private String getFieldValueFromBasicDBObject(Object obj, String fieldName){
        BasicDBObject basicDBObject = (BasicDBObject) obj;
        return basicDBObject.get(fieldName).toString();
    }

    private String getFieldValueFromObject(Object obj, String targetFieldName) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get")) {
                String fieldName = getFieldName(methodName);
                if(fieldName.equals(targetFieldName)){
                    return method.invoke(obj).toString();
                }
            }
        }
        return null;
    }

    /**
     * 通过get/set方法名取得字段名
     *
     * @param methodName
     * @return
     */
    private static String getFieldName(String methodName){
        methodName = methodName.substring(3);
        return lowerCaseFirstLetter(methodName);
    }

    /**
     *首字母转小写
     *
     * @param name
     * @return
     */
    private static String lowerCaseFirstLetter(String name) {
        char[] letters = name.toCharArray();
        if (!isUpperCaseLetter(letters[0])) {
            //不是大写字母，不需要转换，直接返回
            return name;
        }
        //65～90为26个大写英文字母，97～122号为26个小写英文字母
        letters[0] += 32;
        return String.valueOf(letters);
    }

    /**
     * 判断字母是否是大写
     *
     * @param letter
     * @return
     */
    private static boolean isUpperCaseLetter(char letter){
        return letter >= 65 && letter <= 90;
    }

    private String getActionMsg(Object[] args){
        if(args.length == 0){
            return null;
        }
        StringBuilder actionMsg = new StringBuilder();
        for(Object arg : args){
            actionMsg.append(",").append(arg.toString());
        }
        return actionMsg.toString().substring(1);
    }

    public AuditService getAuditService() {
        return auditService;
    }

    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }
}
