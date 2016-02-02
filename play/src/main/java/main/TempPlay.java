package main;

/**
 * Created by hongkai on 2016/2/1.
 */
public class TempPlay {
    public static void main(String args[]){
        String pp = "a*~~*b*~~*c";
        System.out.println(pp);
        String t = "\\*~~\\*";
        for(String a : pp.split(t)){
            System.out.println(a);
        }
    }
}
