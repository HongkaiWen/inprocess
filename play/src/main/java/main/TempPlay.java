package main;

import java.math.BigDecimal;

/**
 * Created by hongkai on 2016/2/1.
 */
public class TempPlay {

    public static void main(String args[]) throws Exception {
        for(int i=1; i<11; i++){
            for(double j=1; j<11; j++){
                System.out.println(String.format("instance: %d, rate: %f, rate2: %f", i, j, calcMaxTps(j, i)));
            }
        }
    }

    private static Double calcMaxTps(Double appMaxTps, int gatewayInstanceNum){
        BigDecimal appMaxTpsBD = new BigDecimal(appMaxTps);
        BigDecimal gatewayInstanceNumBD = new BigDecimal(gatewayInstanceNum);
        //保留两位小数，舍弃第三位小数
        BigDecimal result = appMaxTpsBD.divide(gatewayInstanceNumBD,2,BigDecimal.ROUND_DOWN);
        return result.doubleValue();
    }
}
