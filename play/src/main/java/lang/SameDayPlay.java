package lang;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hongkai on 2016/2/17.
 */
public class SameDayPlay {

    static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
    private static int MILLIS_PER_DAY = 1000*60*60*24;

    public static void main(String args[]){
        Date date1 = new Date();
        Date date2 = new Date();
        long start = System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
//            DateUtils.isSameDay(date1, date2);   //1199
//            isSameDay(date1, date2);   //1137
            isSameDay2(date1, date2);  //6
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static boolean isSameDay(Date date1, Date date2){
        return sf.format(date1).equals(sf.format(date2));
    }

    /**
     * But beware that this fails to take into account changes to the length of day due to daylight savings. But then straight Dates don't encapsulate the notion of time zones so there's no way to non-arbitrarily fix this.
     * - AntoineJ Feb 22 '11 at 14:45
     *
     * http://stackoverflow.com/questions/2517709/comparing-two-dates-to-see-if-they-are-in-the-same-day
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay2(Date date1, Date date2) {
        // Strip out the time part of each date.
        long julianDayNumber1 = date1.getTime() / MILLIS_PER_DAY;
        long julianDayNumber2 = date2.getTime() / MILLIS_PER_DAY;

        // If they now are equal then it is the same day.
        return julianDayNumber1 == julianDayNumber2;
    }

}
