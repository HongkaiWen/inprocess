package main;


import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Created by hongkai on 2016/2/1.
 */
public class TempPlay {

    public static void main(String args[]) throws IOException, InterruptedException, ParseException {
        String url = "http://www.baidu.com";
        String url2 = "http://www.baidu.com/";
        String url3 = "http://www.baidu.com/abc";
        UriComponents uriComponents1 = UriComponentsBuilder.fromHttpUrl(url).build(true);
        UriComponents uriComponents2 = UriComponentsBuilder.fromHttpUrl(url2).build(true);
        UriComponents uriComponents3 = UriComponentsBuilder.fromHttpUrl(url3).build(true);
        System.out.println(uriComponents1.getPath());
        System.out.println(uriComponents2.getPath());
        System.out.println(uriComponents3.getPath());
    }




}
