package concurrent;

/**
 * Created by player on 2016/8/14.
 */
public class T {

    static TestSyncService testSyncService = new TestSyncService();

    public static void main(String args[]){
        testSyncService.sayLove();
        execute(new Runnable() {
            public void run() {
                try {
                    testSyncService.sayHi();
                    testSyncService.sayHi();
                    testSyncService.sayHi();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        execute(new Runnable() {
            public void run() {
                try {
                    testSyncService.play();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        execute(new Runnable() {
            public void run() {
                try {
                    testSyncService.sayBye();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        execute(new Runnable() {
            public void run() {
                try {
                    testSyncService.buy();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        execute(new Runnable() {
            public void run() {
                try {
                    testSyncService.buy();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void execute(Runnable task){
        new Thread(task).start();
    }
}
