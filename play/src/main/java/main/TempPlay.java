package main;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by hongkai on 2016/2/1.
 */
public class TempPlay {

    public static void main(String args[]) throws IOException, InterruptedException, ParseException {
        System.out.println(getTheDayBeforeSeveralDay(2));
    }

    private static Date getTheDayBeforeSeveralDay (int dayCount){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.add(Calendar.DAY_OF_YEAR, (dayCount*-1));

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

}
