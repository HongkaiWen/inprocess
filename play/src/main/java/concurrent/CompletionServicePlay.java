package concurrent;

import java.util.concurrent.*;

/**
 *
 * Created by hongkai on 2016/8/24.
 */
public class CompletionServicePlay {

    public static void main(String args[]) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletionService service = new ExecutorCompletionService(executorService);
        for(int i=0; i<3; i++){
            service.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    TimeUnit.SECONDS.sleep(2);
                    return 3;
                }
            });
        }
        for(int i=0; i<3; i++){
            Future<Integer> take = service.take();
            System.out.println(take.get());
        }
    }

}
