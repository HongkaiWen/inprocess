package audit.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD ,ElementType.PARAMETER, ElementType.TYPE})
public @interface AuditBizRefNo {
    String fieldInObject() default "";
    String fieldInBasicDBObject() default "";
}