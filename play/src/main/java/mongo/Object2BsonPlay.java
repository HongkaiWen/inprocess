package mongo;

import com.mongodb.DBCollection;
import mongo.entity.Student;
import util.DBUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongkai on 2016/2/4.
 */
public class Object2BsonPlay {
    public static void main(String args[]){
        Student student = new Student();
        student.setName("Tom");
        student.setAge(12);
        student.setSex("female");
        List<String> address = new ArrayList<String>();
        address.add("USA");
        address.add("US");
        student.setAddress(address);
        DBUtil dbUtil = DBUtil.getInstance();
        DBCollection s = dbUtil.getColl("student");
        s.insert(student);
    }
}
