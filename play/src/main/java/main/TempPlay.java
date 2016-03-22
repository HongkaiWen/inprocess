package main;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by hongkai on 2016/2/1.
 */
public class TempPlay {
    public static void main(String args[]) throws Exception {
        String[] urls = {"http://www.baidu.com", "http://www.baidu.com:0", "http://www.baidu.com:44", "www.baidu.com",
                "www.baidu.com:80", "45.45.45.45:99", "45.45.45.45:99/xxx/xxx"};

        for(String url : urls){
            System.out.println(url + "  ----->   " +isValidPort(url));
        }

        String email_regx = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+((,|，)[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+)*$|^$)";
        System.out.println("".matches(email_regx));
        System.out.println("java_win@yeah.net".matches(email_regx));
        System.out.println("345633444@qq.com,java_win@yeah.net".matches(email_regx));
        System.out.println("345633444@qq.com,java_win@yeah.net,java_win@yeah.net，java_win@yeah.net".matches(email_regx));
        System.out.println("--------------------------------------------");
        String uri_regx = "^/.*$";
        System.out.println("/abc".matches(uri_regx));
        System.out.println("/".matches(uri_regx));
        System.out.println("/dd/cv".matches(uri_regx));
        System.out.println("/dd/dd/cd/ssdf.xxx".matches(uri_regx));
        System.out.println("--------------------------------------------");
        String temp = "^\\S$";
        System.out.println("\n".matches(temp));

        System.out.println("--------------------------------------------");
        Object abc = null;
        System.out.println(String.valueOf(abc));
        System.out.println("--------------------------------------------");
//        System.out.println(String.valueOf(null));
        System.out.println("--------------------------------------------");
        test();

        String url = "http://www.baidu.com/a/";
        System.out.println(getHostFromUrl(url));
    }

    private static void test() throws Exception {
        Object value = null;
        String fieldName = null;
        try{
//            throw new RuntimeException();
        }catch(Exception e){
            String valueType2String = "";
            String value2String = "";
            fieldName = (fieldName == null ? "" : fieldName);
            if(value != null){
                valueType2String = value.getClass().toString();
                value2String = value.toString();
            }
            System.out.println(String.format("field convert failed: %s, value is %s, type of value is %s",
                    fieldName, value2String, valueType2String));
            throw e;
        }
    }

    private static String getHostFromUrl(String url){
        //去掉http:// https://
        if(url==null){
            return url;
        }

        url=url.replaceAll("(?i)(http://|https://)", "");
        int pos=url.indexOf("/");
        if(pos>0){
            url=url.substring(0,pos);
        }

        return url;
    }

    /**
     * 判断是否是合法的端口号
     * @param url
     * @return
     */
    private static  boolean isValidPort(String url){
        //去除协议信息
        if(url.startsWith("http://")){
            url = url.replace("http://","");
        }
        //截取端口号
        String port = null;
        if(url.contains(":")){
            if(url.contains("/")){
                url = url.substring(0, url.indexOf("/"));
            } else if (url.contains("?")){
                url = url.substring(0, url.indexOf("?"));
            }
            port = url.substring(url.indexOf(":") + 1);
        } else {
           port = "80";
        }
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

}
