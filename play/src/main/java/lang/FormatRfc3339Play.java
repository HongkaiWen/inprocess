package lang;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.text.ParseException;

/**
 * Created by hongkai on 2016/6/13.
 */
public class FormatRfc3339Play {

    public static void main(String args[]) throws ParseException {
        String dateStr = "2016-06-13T01:18:24.637371507Z";

        DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTime();
        DateTime dateTime1 = dateFormatter.parseDateTime(dateStr);
        System.out.println(dateTime1);
        System.out.println(dateTime1.toDate());
    }
}
