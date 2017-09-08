package classloader;

/**
 * Created by hongkai on 2017/9/5.
 */
public class Play {
    public static void main(String args[]) throws ClassNotFoundException {
        System.out.println(Play.class.getClassLoader());

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass = myClassLoader.loadClass("audit.vo.AuditLog");
        System.out.println(aClass);

        System.out.println(aClass.getClassLoader());
    }
}
