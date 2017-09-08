package hystrixplay;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

import java.util.concurrent.Callable;

/**
 * Created by hongkai on 2017/9/7.
 */
public class Test extends HystrixConcurrencyStrategy {

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        String test = "";

        return new Callable<T>() {
            @Override
            public T call() throws Exception {
                //设置值得
                return callable.call();
            }
        };

//        return super.wrapCallable(callable);
    }
}
