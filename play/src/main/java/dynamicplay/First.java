package dynamicplay;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class First {

    static class ClassA {
        public void println(String s){
            System.out.println(s);
        }
    }

    public static void main(String args[]) throws Throwable {
        getPrintlnMH(new ClassA(), "println").invokeExact("abc");
    }

    private static MethodHandle getPrintlnMH(Object receiver, String methodName) throws NoSuchMethodException, IllegalAccessException {
        MethodType mt = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(receiver.getClass(), methodName, mt).bindTo(receiver);
    }
}
