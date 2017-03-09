package lambda.lab;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by hongkai on 2017/3/9.
 */
public class ReducePlay {

    public static void main(String args[]){
        Integer reduce = Stream.of(1, 2, 3).reduce(0, (x, y) -> x + y);
        Optional<Integer> reduce1 = Stream.of(1, 2, 3).reduce((x, y) -> x + y);
        System.out.println(reduce);
        System.out.println(reduce1.get());
    }

}
