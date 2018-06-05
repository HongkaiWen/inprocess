package proxy;

import java.lang.reflect.Proxy;

public class DynamicProxy {

    public static void main(String args[]) {
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler((Subject) () -> System.out.println("xxx")));

        proxySubject.doSomething();

        System.out.println(proxySubject.getClass());

    }

}