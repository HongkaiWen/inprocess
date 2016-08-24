package concurrent;

import java.util.concurrent.*;

/**
 * Created by hongkai on 2016/2/25.
 */
public class StopThreadPlay {
    public static void main(String args[]) throws InterruptedException {
        final BlockingDeque<Object> queue = new LinkedBlockingDeque<Object>();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Runnable a = new Runnable() {
            @Override
            public void run() {
                try {
                    while(!Thread.currentThread().isInterrupted()){
                        queue.take();
//                        TimeUnit.MILLISECONDS.sleep(100);
                        writeDb();
                        System.out.println("write db ok");
                    }
                    System.out.println("正常退出" + Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    System.out.println("中断退出");
                } catch (Exception ex){
                    System.out.println("exception");
                }
            }
        };
        executorService.execute(a);
        System.out.println("等待3秒");
        TimeUnit.SECONDS.sleep(3);
        executorService.shutdownNow();
        boolean result = executorService.awaitTermination(1, TimeUnit.SECONDS);
        while(!result){
            result = executorService.awaitTermination(1, TimeUnit.SECONDS);
        }
    }

    private static void writeDb(){
        try{
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("eat exception");
        }
    }
}
