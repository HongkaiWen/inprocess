package concurrent;

import java.util.concurrent.*;

/**
 * Created by hongkai on 2016/8/24.
 */
public class FuturePlay {

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                throw new RuntimeException("xxx");
//                return 3;
            }
        });
        System.out.println(result.get());
    }
}
