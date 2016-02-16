package solution;

/**
 * limit the times which a user call a api of a app in one second.
 *
 * Created by hongkai on 2016/2/16.
 */
public class LimitRequestByMongo {
    public static void main(String args[]){
        for(int i=0; i<100; i++){
            new Thread(callTask).start();
        }
        new Thread(cleanTask).start();
    }

    private static Runnable cleanTask = new Runnable() {
        @Override
        public void run() {
            //delete from xxx where (time-now) > 60s
        }
    };

    private static Runnable callTask = new Runnable() {
        @Override
        public void run() {
            while(true){
                requestHandler();
            }
        }
    };

    private static void requestHandler(){
        //validate limitation
        // select count(1) from xxx where userName app api and time <= 60s
        //if count > limitation rejected the request and return

        //if count < limitation. insert a new record with necessary and time to xxx collection
        // eg: userName/app/api/time
    }
}
