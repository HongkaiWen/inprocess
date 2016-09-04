package httpclient;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 *
 * Created by player on 2016/9/3.
 */
public class TestConsurrentReq {

    static CountDownLatch startGate = new CountDownLatch(1);

    public static void main(String args[]){
        for(int i=0; i<500; i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        startGate.await();
                    } catch (InterruptedException e) {}
                    reqBaidu();
                }
            }).start();
        }

        startGate.countDown();
        System.out.println("开始测试");
    }

    private static void reqBaidu(){
        String post =
        HttpSender.post("http://mobile-uc.eastdc.cn:82/registry/save.do", 3000, new ArrayList<NameValuePair>(), null, "utf-8");
        System.out.println(post);
    }
}
