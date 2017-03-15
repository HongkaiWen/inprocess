package java8.intf;

/**
 * Created by hongkai on 2017/3/14.
 */
public interface BirdAction {

    void breath();


    default void sayHi(){
        System.out.println("Bird: hi~");
    }
}
