package rxjava;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by player on 2016/10/28.
 */
public class HelloWorld {

    public static void main(String args[]){
        hello("tom", "lucy");
    }


    public static void hello(String... names) {
        Observable.from(names).subscribe(s -> {
            System.out.println("Hello " + s + "!");
        });

//        Observable.from(names).subscribe(new Action1<String>() {
//
//            public void call(String s) {
//                System.out.println("Hello " + s + "!");
//            }
//
//        });

        Observable.just("Hello, world!")
                .map((Func1<String, Object>) s -> s + "----")
                .subscribe(s -> System.out.println(s));
    }
}
