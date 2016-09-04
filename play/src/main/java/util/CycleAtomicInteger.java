package util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * http://ifeve.com/13840/
 *
 */
public class CycleAtomicInteger {
    private final static long PARK_TIME = 1000L * 1000;

    private AtomicInteger counter = new AtomicInteger(0);

    private int range;

    public CycleAtomicInteger(int range) {
        if (range < 2)
        throw new IllegalArgumentException();
        this.range = range;
    }

    /**
     * 获取下个原子值
     *
     * @return
     */
    public int next() {
        for (;;) {
            int c = counter.get();
            int next = (c + 1) % range;
            if (counter.compareAndSet(c, next)) {
                return c;
            } else {
                LockSupport.parkNanos(PARK_TIME);
            }
        }
    }

}