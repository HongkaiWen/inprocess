package lang;

import org.joda.time.DateMidnight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by hongkai on 2016/2/17.
 */
public class ClearTimePlay {

    public static void main(String args[]) throws InterruptedException, ParseException {
//        System.out.println(clearTime3());
//        System.out.println(clearTime4());
        long start = System.currentTimeMillis();
        for(int i=0; i<10000000; i++){
//            clearTime3();
//            DateUtils.truncate(new Date(), Calendar.DATE);
            clearTime(new Date());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static Date clearTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 每天的毫秒数
     */
    private final static int MILLIS_PER_DAY = 1000*60*60*24;

    /**
     * 当前时区偏移量
     */
    private final static int timeZoneOffset = TimeZone.getDefault().getRawOffset();

    /**
     *71 milisecond 10000000 times
     *
     * @return
     */
    public static Date clearTime3(){
        long now = System.currentTimeMillis();
        long today = now - now % MILLIS_PER_DAY - timeZoneOffset;
        return new Date(today);
    }

    /**
     * 831 milisecond 10000000 times
     * @return
     */
    public static Date clearTime4(){
        long milis = new DateMidnight().getMillis();
        return new Date(milis);
    }
}
