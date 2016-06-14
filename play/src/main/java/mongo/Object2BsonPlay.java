package mongo;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import mongo.entity.Student;
import util.DBUtil;
import util.ObjectToDBObjectUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongkai on 2016/2/4.
 */
public class Object2BsonPlay {
    public static void main(String args[]) throws InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        student.setName("xiaoming");
//        student.setAge(12);
        student.setAddress(Arrays.asList("sz","bj","sh"));
        Map<String, String[]> tags = new HashMap<String, String[]>();
        tags.put("edu", new String[]{"sz","bj","sh"});
        student.setTags(tags);
        student.setLastLoginTime(new Date());

        BasicDBList test = new BasicDBList();
        test.add(new BasicDBObject("name","xxx"));
        student.setTest(test);
//        not support my Class
//        List<Teacher> teachers = new ArrayList<Teacher>();
//        teachers.add(new Teacher("zz", 32));
//        teachers.add(new Teacher("aa", 32));
//        student.setTeachers(teachers);

//        DBObject studentObj = MongoUtils.toBasicDBObject(student, "sex");
        DBObject studentObj = ObjectToDBObjectUtil.toBasicDBObject(student, true);
        DBUtil.getInstance().getColl("student").insert(studentObj);
        System.out.println("ok");
    }

    private static void mongoSave(DBObject o){
        //save to mongo
    }

    private static void loadTest(Object o){
        long start = System.currentTimeMillis();
        for(int i=0; i<10000000; i++){
            MongoUtils.toBasicDBObject(o);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
