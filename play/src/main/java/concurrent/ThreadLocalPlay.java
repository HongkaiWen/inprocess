package concurrent;

import mongo.entity.Student;

/**
 * Created by hongkai on 2016/8/2.
 */
public class ThreadLocalPlay {

    private static ThreadLocal<Student> student = null;

    static{
        student = new ThreadLocal<Student>();
        student.set(new Student());
    }

    public static void main(String args[]){
        new ThreadLocalPlay().test();
    }

    private void test(){
        Student student1 = student.get();
        System.out.println(student1);
        student1.setName("xxx");
        System.out.println(student1.getName());
    }
}
