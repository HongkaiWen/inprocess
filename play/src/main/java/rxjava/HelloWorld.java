package rxjava;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by player on 2016/10/28.
 */
public class HelloWorld {

    public static void main(String args[]){
        hello("tom", "lucy");
    }


    public static void hello(String... names) {

//        Observable.from(names).subscribe(new Action1<String>() {
//
//            public void call(String s) {
//                System.out.println("Hello " + s + "!");
//            }
//
//        });

//        Observable.from(names).subscribe(s -> {
//            System.out.println("Hello " + s + "!");
//        });
//
//        Observable.just("Hello, world!")
//                .map((Func1<String, Object>) s -> s + "----")
//                .subscribe(s -> System.out.println(s));
//
//        query("abc").flatMap(urls -> Observable.from(urls))
//                .subscribe(url -> System.out.println(url));
//
//        getTitle("baidu").subscribe(title -> System.out.println(title));

        query("abc").flatMap(urls -> Observable.from(urls))
                .flatMap(url -> getTitle(url))
                .filter(title -> title != null).take(2).doOnNext(title -> save(title))
                .doOnCompleted(() -> System.out.println("complete"))
                .subscribe(title -> System.out.println(title));

    }

    public static void save(String title){
        System.out.println("save " + title);
    }

    public static Observable<String> getTitle(String url){
        String result = null;
        if(url.contains("baidu")){
            result = "sb website";
        } else if(url.contains("google")){
            result = "greate website";
        } else if(url.contains("face")){
            result = "face website";
        } else if(url.contains("twitter")){
            result = "twitter website";
        } else {
            result = null;
        }
        return Observable.just(result);
    }

    public static Observable<List<String>> query(String keyWord){
        List<String> result = new ArrayList<>();
        if(keyWord.equals("abc")){
            result.add("https://www.google.com");
            result.add("https://www.twitter.com");
            result.add("https://www.facebook.com");
            result.add("https://www.qq.com");
        } else {
            result.add("https://www.facebook.com");
            result.add("https://www.qq.com");
        }
        return Observable.just(result);
    }
}
