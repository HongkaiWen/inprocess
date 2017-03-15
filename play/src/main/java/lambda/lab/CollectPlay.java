package lambda.lab;

import lambda.entity.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hongkai on 2017/3/15.
 */
public class CollectPlay {

    public static void main(String args[]){
        //value convert
        Stream<Student> studentStream = Stream.of(new Student("Tom", 18, false),
                new Student("Lucy", 17, true),
                new Student("LiLei", 18, false),
                new Student("Lily", 17, true));

        Map<Boolean, List<Student>> collect = studentStream.collect(Collectors.partitioningBy(Student::isFemale));

        studentStream = Stream.of(new Student("Tom", 18, false),
                new Student("Lucy", 17, true),
                new Student("LiLei", 18, false),
                new Student("Lily", 17, true));

        Map<Integer, List<Student>> collect1 = studentStream.collect(Collectors.groupingBy(Student::getAge));

        studentStream = Stream.of(new Student("Tom", 18, false),
                new Student("Lucy", 17, true),
                new Student("LiLei", 18, false),
                new Student("Lily", 17, true));

        Map<Integer, Long> collect2 = studentStream.collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));


        printMap(collect);
        System.out.println();
        printMap(collect1);
        System.out.println();
        printMap(collect2);

    }

    private static void printMap(Map<?,?> map){
        for(Map.Entry<?,?> o : map.entrySet()){
            Object key = o.getKey();
            Object value = o.getValue();

            System.out.println(key + "--> ");
            if(value instanceof List){
                List v = (List) value;
                for(Object o2 : v){
                    System.out.println(o2);
                }
            } else {
                System.out.println(value);
            }
        }
    }

}
