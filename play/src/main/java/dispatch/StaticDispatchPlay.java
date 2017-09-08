package dispatch;

public class StaticDispatchPlay {

    static abstract class Human {}

    static class Man extends Human{}

    static class Woman extends Human{}

    public void sayHello(Human guy){
        System.out.println("hi, guy!");
    }

    public void sayHello(Man guy){
        System.out.println("hi, man!");
    }

    public void sayHello(Woman guy){
        System.out.println("hi, woman!");
    }

    public static void main(String args[]){
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatchPlay staticDispatchPlay = new StaticDispatchPlay();
        staticDispatchPlay.sayHello(man);
        staticDispatchPlay.sayHello(woman);
        staticDispatchPlay.sayHello((Woman) woman);
    }

}
