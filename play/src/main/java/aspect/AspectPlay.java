package aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hongkai on 2016/6/15.
 */
public class AspectPlay {
    public static void main(String args[]){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aspect.xml");
        MyService customer = (MyService) applicationContext.getBean("myService");
        customer.test();
    }
}
