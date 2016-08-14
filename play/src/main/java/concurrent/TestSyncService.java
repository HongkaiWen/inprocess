package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by player on 2016/8/14.
 */
public class TestSyncService {

    /**
     * 重入锁
     */
    public synchronized void sayLove(){
        //如果不是可重入锁，此处即为死锁
        System.out.println("I love you ~");
        marry();
    }

    private synchronized void marry(){
        System.out.println("marry ~");
    }

    /**
     * 方法1 2 3 方法级synchronized, 持有this对象为锁对象，所以三个方法互斥执行
     *
     * method 1
     * @throws InterruptedException
     */
    public synchronized void sayHi() throws InterruptedException {
        System.out.println("hi ~");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("hi ~");
    }

    /**
     * method 2
     * @throws InterruptedException
     */
    public synchronized void play() throws InterruptedException {
        System.out.println("play ~");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("play ~");
    }

    /**
     * method 3
     * @throws InterruptedException
     */
    public synchronized void sayBye() throws InterruptedException {
        System.out.println("bye ~");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("bye ~");
    }

    private Object bed = new Object();

    /**
     * buy 方法，持有的锁对象为bed，与前三个方法不同，可并行执行
     *
     * @throws InterruptedException
     */
    public void buy() throws InterruptedException {
        synchronized (bed){
            System.out.println("buy a bed");
            TimeUnit.SECONDS.sleep(3);
        }
    }
}
