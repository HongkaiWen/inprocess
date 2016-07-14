package guava.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * google RateLimiter play
 *
 * Created by hongkai on 2016/7/5.
 */
public class RateLimitPlay {

    //10 concurrent is permitted
    private static Double QPS = 10.00;
    //MILLISECONDS
    private static Integer TPR = 500;
    //second
    private static Integer TEST_TIME = 300;

    private static RateLimiter rateLimiter = RateLimiter.create(QPS, 0, TimeUnit.SECONDS);

    private static List<Long> passRequestTimes = Collections.synchronizedList(new ArrayList<Long>());

    private static CountDownLatch  startGate = new CountDownLatch(1);

    private static ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            0L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    private static Long testStartTime;
    private static Long testEndTime;

    public static void main(String args[]) throws InterruptedException, IOException {
        //500 concurrent
        prepareTestTasks(500);

        //start test after 5 seconds
        testStartTime = startTest(5, new TimerTask() {
            @Override
            public void run() {
                startGate.countDown();
            }
        });
        System.out.println("test start after 5 seconds...");
        System.out.println("start time: " + testStartTime);

        //calculate test end time
        calcEndTime();

        System.out.println("end time: " + testEndTime);

        TimeUnit.SECONDS.sleep(TEST_TIME);
        System.out.println("test over, wait statics data");
        TimeUnit.SECONDS.sleep(TEST_TIME/2);
        System.out.println("statics data ready, start statics...");
        executorService.shutdownNow();

        int passCount = passRequestTimes.size();
        double qps = Double.valueOf(passCount)/Double.valueOf(TEST_TIME);
        System.out.println(String.format("test finished. during %d seconds, pass request is %d, qps is %s",
                TEST_TIME,
                passCount,
                String.valueOf(qps)));
        statics();

        printRequestLog();
    }

    private static void printRequestLog() throws IOException {
        File logFile = new File("e:/temp/requestLog.log");
        BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
        for(Long requestTime : passRequestTimes){
            writer.write(String.valueOf(requestTime) + "\n");
        }
        writer.flush();
        writer.close();
    }

    private static void prepareTestTasks(int concurrent){
        for(int i=0; i<concurrent; i++){
            executorService.execute(new Runnable() {

                public void run() {
                    try {
                        startGate.await();
                        while(!Thread.currentThread().isInterrupted() &&
                                System.currentTimeMillis() <= testEndTime){
//                            boolean acquire = rateLimiter.tryAcquire(1, 1, TimeUnit.SECONDS);
                            boolean acquire = rateLimiter.tryAcquire(1);
                            if(!acquire){
                                continue;
                            }
                            recordRequest();
                            taskHanlder();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
    }

    /**
     * start test
     *
     * @param executeAfterSeconds
     * @return
     */
    private static Long startTest(int executeAfterSeconds, TimerTask task){
        Timer timer = new Timer();
        Long currentTime = System.currentTimeMillis();
        Long delay = executeAfterSeconds*1000L;
        Long startTime = currentTime + delay;
        timer.schedule(task, delay);
        return startTime;
    }

    private static void calcEndTime(){
        testEndTime = testStartTime + (TEST_TIME*1000L);
    }

    private static void taskHanlder() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(TPR);
    }

    private static void recordRequest(){
        executorService.execute(new Runnable() {

            public void run() {
                passRequestTimes.add(System.currentTimeMillis());
            }
        });
    }

    private static void statics(){
        //sort
        Collections.sort(passRequestTimes, new Comparator<Long>() {

            public int compare(Long o1, Long o2) {
                return (int) (o1 - o2);
            }
        });

        for(int i=0; i<passRequestTimes.size(); i++){

            for(int j=i; j<passRequestTimes.size(); j++){

                long startTime = passRequestTimes.get(i);
                long endTime = passRequestTimes.get(j);


                if((endTime - startTime) <= 1000L && (j-i) > 10){

                    System.out.println(String.format("----request over qps, %d ----", j-i));
                    for(int x=i; x<=j; x++){
                        System.out.println(passRequestTimes.get(x));
                    }
                    continue;
                }
            }
        }
    }


}
