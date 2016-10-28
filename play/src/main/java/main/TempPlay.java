package main;


import com.mongodb.BasicDBObject;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongkai on 2016/2/1.
 */
public class TempPlay {

    private static final Integer count = 0;

    public static void main(String args[]) throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        for(int a=1; a<=103; a++){
            list.add(a);
        }
        print(list);
    }

    public static void print(List<Integer> list){
        int maxMsgCountPerPush = 10;
        int msgCount = list.size();
        System.out.println(msgCount);
        for(int startIndex=0, endIndex=maxMsgCountPerPush; endIndex <= msgCount; startIndex+=maxMsgCountPerPush, endIndex = (endIndex+maxMsgCountPerPush>msgCount) ? msgCount : endIndex+maxMsgCountPerPush){
            List<Integer> listPerPush = list.subList(startIndex, endIndex);
            printItem(listPerPush);
        }
    }

    public static void printItem(List<Integer> list){
        System.out.println("----------------------------");
        for(Integer a : list){
            System.out.println(a);
        }
    }

}
