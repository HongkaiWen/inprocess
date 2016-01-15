package main;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * http://www.joda.org/
 *
 * Created by hongkai on 2016/1/15.
 */
public class ISODatePlay {
    public static void main(String args[]){
        //ISO format string to java.util.Date
        String dateStr = "2015-12-25T10:12:15.078581168Z";
        Date date = new DateTime(dateStr).toDate();
        System.out.println(date);
    }
}
