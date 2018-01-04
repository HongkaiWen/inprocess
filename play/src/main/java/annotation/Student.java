package annotation;

import java.lang.annotation.*;

/**
 * student
 *
 * @author hongkai
 * @create 2018-01-03 下午10:42
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Student {

    String name() default "Xiao ming";

}