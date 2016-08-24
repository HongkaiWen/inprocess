package ratelimit.my;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hongkai on 2016/7/14.
 */
public class Play {

    private static final int maxRatePerSecond = 100;
    private static final int concurrent = 1000;
    private static final int testTime = 20;

    private static AtomicInteger successCount = new AtomicInteger();
    private static AtomicInteger failedCount = new AtomicInteger();
    private static AtomicLong successDelay = new AtomicLong();
    private static AtomicLong failedDelay = new AtomicLong();
    private static AtomicLong successMaxDelayTime = new AtomicLong(0);
    private static AtomicLong failedMaxDelayTime = new AtomicLong(0);


//    static UnSortDelayQueue<RequestHistory> requests = new UnSortDelayQueue<RequestHistory>(maxRatePerSecond);
    static UnSortDelayQueue<RequestHistory> requests = null;

    private static CountDownLatch startGate = new CountDownLatch(1);

    public static void main(String args[]) throws InterruptedException {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        requests.take();
                    } catch (InterruptedException e) {
                        System.out.println("take finish.");
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        t.start();

        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                0L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        for(int i=0; i<concurrent; i++){
            executorService.execute(new Runnable() {

                public void run() {
                    try {
                        startGate.await();
                        while(!Thread.currentThread().isInterrupted()){
                            Long current = System.currentTimeMillis();
                            boolean add = requests.add(new RequestHistory());
                            Long executeTime = System.currentTimeMillis();
                            long delayTime = executeTime - current;
                            if(!add){
                                long old = successMaxDelayTime.longValue();
                                if(delayTime > old){
                                    successMaxDelayTime.compareAndSet(old, delayTime);
                                }
                                failedDelay.addAndGet(delayTime);
                                failedCount.incrementAndGet();
                                continue;
                            }
                            long old = failedMaxDelayTime.longValue();
                            if(delayTime > old){
                                failedMaxDelayTime.compareAndSet(old, delayTime);
                            }
                            successCount.incrementAndGet();
                            successDelay.addAndGet(delayTime);
                            TimeUnit.MILLISECONDS.sleep(100);
                        }
                    } catch (InterruptedException e) {
                        System.out.println("put finish.");
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        startGate.countDown();

        TimeUnit.SECONDS.sleep(testTime);

        executorService.shutdown();
        t.interrupt();

        System.out.println("测试结束，开始统计...");
        System.out.println(String.format("测试时间：%d 秒， 并发： %d， 允许最大速度： %d/秒",
                testTime, concurrent, maxRatePerSecond));
        System.out.println("成功数量： " + successCount.intValue());
        System.out.println("失败数量： " + failedCount.intValue());
        System.out.println("成功请求平均延迟（毫秒）： " + successDelay.longValue()/successCount.intValue());
        System.out.println("成功最大延迟（毫秒）：" + successMaxDelayTime.longValue());
        System.out.println("失败请求平均延迟（毫秒）： " + failedDelay.longValue()/failedCount.intValue());
        System.out.println("失败最大延迟（毫秒）：" + failedMaxDelayTime.longValue());




//        Long sum = 0L;
//        for(Long l : successLog){
//            sum += l;
//        }
//
//        System.out.println(String.format("测试时间：%d 秒， 并发： %d， 允许最大速度： %d/秒",
//                maxRatePerSecond, concurrent, testTime));
//
//        Long max = Collections.max(successLog);
//        Long min = Collections.min(successLog);
//        int total = successLog.size();
//
//        Long avg = sum/total;
//        System.out.println(String.format("成功请求延迟时间： max %d, min %d, avg %d, 成功总数 %d", max, min, avg, total));
//
//        Long failedSum = 0L;
//        for(Long l : failedLog){
//            failedSum += l;
//        }
//
//        Long failedMax = Collections.max(failedLog);
//        Long failedMin = Collections.min(failedLog);
//        int failedTotal = failedLog.size();
//        Long failedAvg = failedSum/failedTotal;
//        System.out.println(String.format("失败请求延迟时间： max %d, min %d, avg %d, 失败总数 %d",
//                failedMax, failedMin, failedAvg, total));


    }
}
