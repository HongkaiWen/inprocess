package classloader;

import java.io.FileInputStream;

/**
 * Created by hongkai on 2017/9/5.
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if (c != null) {
            return c;
        }
        ClassLoader parent = this.getParent();
        try {
            c = parent.loadClass(name);
        } catch (Exception e) {
        }

        if (c != null) {
            return c;
        }
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            c = defineClass(name, classData, 0, classData.length);
        }

        return c;
    }

    private byte[] getClassData(String name) {
        try {
            FileInputStream fileInputStream = new FileInputStream("E:\\code\\github\\inprocess\\play\\target\\classes\\audit\\vo\\AuditLog.class");
            byte[] classData = new byte[fileInputStream.available()];
            fileInputStream.read(classData);
            return classData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
