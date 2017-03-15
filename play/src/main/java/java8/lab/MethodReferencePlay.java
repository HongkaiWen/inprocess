package java8.lab;

import lambda.entity.Student;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Created by hongkai on 2017/3/15.
 */
public class MethodReferencePlay {

    public static void main(String args[]){
        Student minAgeStudent = Stream.of(new Student("Tom", 12), new Student("Lucy", 13))
                .min(Comparator.comparing(Student::getAge)).get();
        System.out.println(minAgeStudent);
    }
}
