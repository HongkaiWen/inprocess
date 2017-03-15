package java8.lab;

import java.util.Optional;

/**
 * Created by hongkai on 2017/3/15.
 */
public class OptionalPlay {

    public static void main(String args[]) throws Exception {
        Optional<String> a = Optional.of("a");
        System.out.println("a".equals(a.get()));
        System.out.println(a.isPresent());

        Optional<Object> b = Optional.empty();
        Optional<Object> c = Optional.ofNullable(null);
        System.out.println(b.isPresent());
        System.out.println(c.isPresent());

        System.out.println(b.orElse("b"));
        System.out.println(c.orElseGet(() -> "c"));
        System.out.println(c.orElseThrow(() -> new Exception("c is null")));



    }
}
