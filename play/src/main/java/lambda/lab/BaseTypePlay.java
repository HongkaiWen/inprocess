package lambda.lab;

import lambda.entity.Student;
import lambda.entity.Students;

import java.util.IntSummaryStatistics;

/**
 * 基本类型
 *
 * Created by hongkai on 2017/3/13.
 */
public class BaseTypePlay {

    public static void main(String args[]){
        Students students = new Students();
        for(int i=0; i<10000; i++){
            students.addStudeng(new Student("Tom" + 1, i));
        }

        IntSummaryStatistics intSummaryStatistics =
                students.getStudents()
                        .stream().mapToInt(student -> student.getAge())
                        .summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getSum());

    }

}
