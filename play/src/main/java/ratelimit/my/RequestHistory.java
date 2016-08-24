package ratelimit.my;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by hongkai on 2016/7/14.
 */
public class RequestHistory implements Delayed {

    private Long delayTime;

    public RequestHistory(){
        delayTime = TimeUnit.NANOSECONDS.convert(1L, TimeUnit.SECONDS) + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delayTime - System.nanoTime(),  TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        RequestHistory compareRequestHistory = (RequestHistory) o;
        if(this.delayTime > compareRequestHistory.delayTime){
            return 1;
        }else if(this.delayTime == compareRequestHistory.delayTime){
            return 0;
        }else{
            return -1;
        }
    }
}
