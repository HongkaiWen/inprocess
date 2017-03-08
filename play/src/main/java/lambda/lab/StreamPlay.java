package lambda.lab;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hongkai on 2017/3/7.
 */
public class StreamPlay {

    public static void main(String args[]){
        List<String> source = Stream.of("a", "b", "c").collect(Collectors.toList());
        List result = source.stream().map(src -> src.toUpperCase()).collect(Collectors.toList());
        result.forEach(src -> System.out.println(src));
    }
}
