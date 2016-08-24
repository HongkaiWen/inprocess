package ratelimit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by hongkai on 2016/7/8.
 */
public class RateLimitPlay3 {

    public static void main(String args[]) throws InterruptedException {
        final RateLimiter limiter = RateLimiter.create(3);
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire(2));
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire(3));
        System.out.println(limiter.acquire());
    }

}
