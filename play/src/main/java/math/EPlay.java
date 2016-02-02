package math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * calculate the e
 *
 * Created by hongkai on 2016/2/2.
 */
public class EPlay {

    public static void main(String args[]){
        int start = 10000;
        int count = 1;
        for(int i=start; i<(start + count); i++){
            String result = String.format("(1+1/%d)^%d result is %f", i, i, calc(i));
            System.out.println(result);
        }
    }

    private static float calc(int n){
        BigDecimal n_ = new BigDecimal(n);
        BigDecimal ONE = new BigDecimal(1);
        return (ONE.add(ONE.divide(n_,10, RoundingMode.HALF_UP))).pow(n).floatValue();
    }

}
