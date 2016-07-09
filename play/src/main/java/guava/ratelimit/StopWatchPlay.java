package guava.ratelimit;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by player on 2016/7/10.
 */
public class StopWatchPlay {

    public static void main(String args[]) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.SECONDS.sleep(3);
        stopwatch.stop();
        Long elapsedTime = stopwatch.elapsed(TimeUnit.NANOSECONDS);
        System.out.println(elapsedTime);
    }
}
