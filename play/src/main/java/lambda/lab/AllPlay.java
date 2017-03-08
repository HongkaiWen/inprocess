package lambda.lab;

import lambda.entity.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hongkai on 2017/3/8.
 */
public class AllPlay {

    public static void main(String args[]) {
        //stream to list
        List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());
        list.forEach(letter -> System.out.println(letter));

        System.out.println();

        //stream to set
        Set<String> set = Stream.of(Arrays.asList("a", "b"), Arrays.asList("c", "b"))
                .flatMap(subList -> subList.stream()).collect(Collectors.toSet());
        set.forEach(letter -> System.out.println(letter));

        System.out.println();

        //map play
        Stream.of("a", "b", "c").map(letter -> letter.toUpperCase())
                .forEach(letter -> System.out.println(letter));

        System.out.println();

        //flat map play
        Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream()).forEach(number -> System.out.println(number));

        System.out.println();

        //min play
        Student minAgeStudent = Stream.of(new Student("Tom", 12), new Student("Lucy", 13))
                .min(Comparator.comparing(student -> student.getAge())).get();
        System.out.println(minAgeStudent);


    }

}
