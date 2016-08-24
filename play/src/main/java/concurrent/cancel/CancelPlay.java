package concurrent.cancel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by player on 2016/8/25.
 */
public class CancelPlay {
    public static void main(String args[]) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0; i<10; i++){
            executorService.execute(new Runnable() {
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println("done");
                    } catch (InterruptedException e) {
//                        System.out.println(Thread.currentThread().isInterrupted());
                        Thread.currentThread().interrupt();
                        System.out.println(Thread.currentThread().isInterrupted());
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }
}
