package lambda.lab;

import lambda.entity.Student;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by hongkai on 2017/3/8.
 */
public class ComparatorPlay {

    public static void main(String args[]){
        //双冒号，学名 “方法引用”， 表示返回一个java.util.function.Function
        //如下面的例子，Student::getAge 表示一个 Function<Student,Integer>
        Comparator<Student> byAge = Comparator.comparing(Student::getAge);
        Student minAgeStudent = Stream.of(new Student("Tom", 12), new Student("Lucy", 13))
                .min(byAge).get();
        System.out.println(minAgeStudent);

        minAgeStudent = Stream.of(new Student("Tom", 12), new Student("Lucy", 13))
                .min(comparing(student -> student.getAge())).get();
        System.out.println(minAgeStudent);
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
            Function<? super T, ? extends U> keyExtractor)
    {
        Objects.requireNonNull(keyExtractor);
        return (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
    }
}
