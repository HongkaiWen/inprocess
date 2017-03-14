package java8.intf;

/**
 *
 *
 * note: 接口是一种行为
 *
 * Created by hongkai on 2017/3/14.
 */
public interface ManAction {

    void breath();

    default void sayHi(){
        System.out.println("Person: hi~");
    }

    static void hi(ManAction manAction){
        manAction.sayHi();
    }
}
