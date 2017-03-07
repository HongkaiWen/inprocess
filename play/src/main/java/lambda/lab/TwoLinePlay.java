package lambda.lab;

import lambda.intf.Filter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by hongkai on 2017/3/7.
 */
public class TwoLinePlay {

    public static void main(String args[]){
        List<String> target = Arrays.asList("zs", "ls", "ww");
        print(target, str -> {
            if(str.equals("jjj")){
                return false;
            }
            return true;
        });
    }

    private static void print(Collection<String> target, Filter filter){
        for(String temp : target){
            if(filter.accept(temp)){
                System.out.println(temp);
            }
        }
    }
}
