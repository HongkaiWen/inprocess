package lambda.lab;

import lambda.intf.Filter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 *
 * Created by hongkai on 2017/3/7.
 */
public class FirstPlay {

    public static void main(String args[]){
        List<String> target = Arrays.asList("zs", "ls", "ww");
        print(target, src -> !"zs".equals(src));
    }

    private static void print(Collection<String> target, Filter filter){
        for(String temp : target){
            if(filter.accept(temp)){
                System.out.println(temp);
            }
        }
    }
}
