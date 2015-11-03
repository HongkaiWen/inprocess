package test;

import java.util.concurrent.TimeUnit;

/**
 * Created by player on 2015/11/3.
 */
public class TestString {


    public static void main(String args[]){
        //this is not lock the same object
//        final User u1 = new User(new String("u1"));
//        final User u2 = new User(new String("u1"));

        //lock the same object
        final User u1 = new User("u1");
        final User u2 = new User("u1");
        new Thread(new Runnable() {
            public void run() {
                test(u1.getName());
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                test(u2.getName());
            }
        }).start();
    }

    private static void test(String name){
        synchronized (name){
            System.out.println("start");
            try{
                TimeUnit.SECONDS.sleep(10);
            }catch(Exception e){

            }
            System.out.println("end");
        }
    }

}

class User{
    private String name;

    public String getName(){
        return name;
    }

    public User(String name){
        this.name = name;
    }
}
