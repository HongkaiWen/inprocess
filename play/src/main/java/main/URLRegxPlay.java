package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 匹配
 * http:// 可选
 * 域名 or ip
 * 端口 可选
 *
 * Created by hongkai on 2016/1/19.
 */
public class URLRegxPlay {

    public static void main(String args[]) {
        String[] urls = {"http://www.baidu.com","http://www.baidu.com.","http://dceast.com:84",
                "http://192.123.43.22:80","http://193.23","www.baidu.com.", "www.baidu.com", "www.dddddddddd.xxxxxxxxxxxxx"};
        for(String url : urls){
            System.out.println(url + "  ---  " + isValidUrl(url));
        }
    }

    private static final String ipRegx = "(?:[0-9]{1,3}\\.){3}[0-9]{1,3}";
    private static final String portRegx = ":\\d+";
    private static final String domain = "[-a-zA-Z0-9@:%._\\+~#=]{1,1024}\\.[a-z]+";

    private static boolean isValidUrl(String url) {
        String urlRegx = String.format("^(http://)?(%s|%s)(%s)?$", domain, ipRegx, portRegx);
        Pattern pattern = Pattern.compile(urlRegx, Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(url);
        return m.find();
    }
}
