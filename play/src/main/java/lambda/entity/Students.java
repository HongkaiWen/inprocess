package lambda.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生们
 *
 * Created by hongkai on 2017/3/13.
 */
public class Students {

    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudeng(Student student){
        if(students == null){
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
