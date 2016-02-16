package main;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hongkai on 2016/2/1.
 */
public class TempPlay {

    private static TreeMap<Long, Date> costs = new TreeMap<Long, Date>(new Comparator<Long>(){
        @Override
        public int compare(Long o1, Long o2) {
            if(o1 > o2)
                return -1;
            if(o1 < o2)
                return 1;
            else
                return 0;
        }
    });

    public static void main(String args[]){
        costs.put(23L, new Date());
        costs.put(24L, new Date());
        costs.put(25L, new Date());
        for(Map.Entry<Long, Date> entry : costs.entrySet()){
            System.out.println(entry.getKey());
        }
    }
}
