package regex;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hongkai on 2016/3/17.
 */
public class UrlCheck {
    public static void main(String args[]){
        String url = "www.baidu.com/";
        System.out.println(isValidUrl(url));

        String[] urls = {"http://www.baidu.com", "http://www.baidu.com:8080", "www.baidu.com",
                "http://10.23.33.21", "12.34.21.34", "http://12.45.32.21:3456", "12.34.21.34:3322",
                "12.34.21.34:3322/xxx","http://www.baidu.com:80/xxx", "http://www.baidu.com:80?abc=222",
                "http://www.baidu.com:8033333/?abc=222", "http://www.baidu.com/?abc=222"};
        for(String tmp : urls){
            System.out.println(tmp + "   ---->  " + isValidUrl(tmp));
        }
        System.out.println("----------------below is test for port -----------------");
        String[] urls_ = {"www.baidu.com",
                "www.baidu.com:56",
                "www.baidu.com:56/",
                "www.baidu.com:56/?abc=abc",
                "www.baidu.com:56/dfss/?abc=abc",
                "http://www.baidu.com",
                "http://www.baidu.com:56",
                "http://www.baidu.com:56/",
                "http://www.baidu.com:56/?abc=abc",
                "http://www.baidu.com:56/dfss/?abc=abc",
                "https://www.baidu.com",
                "https://www.baidu.com:56",
                "https://www.baidu.com:56/",
                "https://www.baidu.com:56/?abc=abc",
                "https://www.baidu.com:56/dfss/?abc=abc",
                "www.baidu.com:65536",
                "www.baidu.com:65536/",
                "www.baidu.com:65536/?abc=abc",
                "www.baidu.com:65536/dfss/?abc=abc",
                "http://www.baidu.com:65536",
                "http://www.baidu.com:65536/",
                "http://www.baidu.com:65536/?abc=abc",
                "http://www.baidu.com:65536/dfss/?abc=abc",
                "https://www.baidu.com:65536",
                "https://www.baidu.com:65536/",
                "https://www.baidu.com:65536/?abc=abc",
                "https://www.baidu.com:65536/dfss/?abc=abc"
        };
        for(String tmp : urls_){
            System.out.println(tmp + "   ---->  " + isValidPort(tmp)  + "   --->   " + getPort(tmp));
        }
    }

    /**
     * 检查url格式
     * http://ip|domain[:port][/path]
     * @param url
     * @return
     */
    private static  boolean isValidUrl(String url) {
        if(StringUtils.isEmpty(url)){
            return false;
        }
        String urlRegx = String.format("^(http://)?(%s|%s)(%s)?(/|(/[\\S]+))?$", domain, ipRegxPart, portRegx);
        Pattern pattern = Pattern.compile(urlRegx, Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(url);
        return m.find() && isValidPort(url);
    }

    /**
     * 判断是否是合法的端口号
     * @param url
     * @return
     */
    private static  boolean isValidPort(String url){
        //截取端口号
        String port = getPort(url);
        //非数值类型，返回false
        if(!StringUtils.isNumeric(port)){
            return false;
        }
        int portInt = Integer.valueOf(port);
        //判断端口范围
        if(portInt > 65535 || portInt < 0){
            return false;
        }
        return true;
    }

    private static String getPort(String url){
        //去除协议信息
        if(url.startsWith("http://")){
            url = url.replace("http://","");
        }
        if(url.startsWith("https://")){
            url = url.replace("https://","");
        }
        //截取端口号
        String port = null;
        if(url.contains(":")){
            if(url.contains("/")){
                url = url.substring(0, url.indexOf("/"));
            }
            port = url.substring(url.indexOf(":") + 1);
        } else {
            port = "80";
        }
        return port.trim();
    }

    private static final String ipRegxPart = "(?:[0-9]{1,3}\\.){3}[0-9]{1,3}";
    private static final String portRegx = ":\\d+";
    private static final String domain = "[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}";
}
