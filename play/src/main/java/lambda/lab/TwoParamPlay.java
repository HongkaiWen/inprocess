package lambda.lab;

import lambda.intf.Compare;

/**
 * Created by hongkai on 2017/3/7.
 */
public class TwoParamPlay {

    public static void main(String args[]){
        int a = 1;
        int b = 2;
        int bigger = getBigger(a, b, (a1, b1) -> a1 > b1);
        System.out.println(bigger);
    }

    private static int getBigger(int a, int b, Compare compare){
        if(compare.biggerThan(a, b)){
            return a;
        } else {
            return b;
        }
    }
}
