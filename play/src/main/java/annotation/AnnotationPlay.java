package annotation;

/**
 * @author hongkai
 * @create 2018-01-03 下午10:46
 **/
public class AnnotationPlay {

    public static void main(String args[]){
        System.out.println(Student.class.isAnnotation());
        System.out.println(LiLei.class.isAnnotationPresent(Student.class));
    }
}
