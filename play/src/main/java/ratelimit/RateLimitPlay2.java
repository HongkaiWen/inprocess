package ratelimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.*;

/**
 * Created by hongkai on 2016/7/8.
 */
public class RateLimitPlay2 {

    private static RateLimiter rateLimiter = RateLimiter.create(10);
    private static CountDownLatch startGate = new CountDownLatch(1);
    private static CountDownLatch startGate2 = new CountDownLatch(1);

    public static void main(String args[]) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                0L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        for(int i=0; i<100; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        boolean b = rateLimiter.tryAcquire(1);
                        if(b){
                            System.out.println("pass");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        for(int i=0; i<500; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate2.await();
                        boolean b = rateLimiter.tryAcquire(1);
                        if(b){
                            System.out.println("pass");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        TimeUnit.SECONDS.sleep(1);
        startGate.countDown();
        TimeUnit.SECONDS.sleep(1);
        startGate2.countDown();
        TimeUnit.SECONDS.sleep(3);
        executorService.shutdownNow();
    }
}
