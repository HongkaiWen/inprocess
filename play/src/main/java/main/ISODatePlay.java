package main;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * http://www.joda.org/
 * http://stackoverflow.com/questions/2201925/converting-iso-8601-compliant-string-to-java-util-date
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
