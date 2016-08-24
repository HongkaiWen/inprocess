package concurrent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

/**
 * Created by hongkai on 2016/7/13.
 */
public class LimitConnection {
    private Semaphore semaphore;

    public LimitConnection(int connectionCount){
        semaphore = new Semaphore(connectionCount);
    }

    public boolean tryAcquireAndRelease(long releaseTimeMillis){
        boolean acquire = semaphore.tryAcquire();
        if(acquire){
            new Timer().schedule(new ReleaseTask(semaphore), releaseTimeMillis);
        }
        return acquire;
    }
}

class ReleaseTask extends TimerTask {

    private Semaphore semaphore;

    ReleaseTask(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        semaphore.release();
    }
}