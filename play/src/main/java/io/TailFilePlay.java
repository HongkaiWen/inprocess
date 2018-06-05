package io;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * tail 文件
 *
 * @author hongkai
 * @create 2018-05-14 4:43 PM
 **/
public class TailFilePlay {

    public static void main (String args[]) throws InterruptedException {
        Tailer.create(new File("/Users/hongkai/tmp/log.test"), new MyListener());
        TimeUnit.SECONDS.sleep(3000);
    }

}

class MyListener extends TailerListenerAdapter {

    @Override
    public void handle(String line) {
        System.out.println(line);
    }
}
