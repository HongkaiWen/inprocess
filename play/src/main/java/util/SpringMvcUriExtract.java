package util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据spring mvc启动日志，抽取系统uri
 *
 * Created by hongkai on 2017/3/14.
 */
public class SpringMvcUriExtract {

    public static final String SUFFIX = ".do";

    public static final String REGEX = "\\[(/[a-zA-Z0-9.-_]+)+]";

    public static final String LOG_PATH = "C:\\Users\\hongkai\\Desktop\\log-paas.txt";

    private static Integer counter = 1;
    private static String preffix = "URL_10";

    public static void main(String args[]) throws IOException {
        FileUtils.readLines(new File(LOG_PATH), "utf-8").stream()   //read log file
                .filter(line -> line.contains("RequestMappingHandlerMapping")) //filter proper line
                .filter(line -> line != null && line.trim().length() != 0)  //filter blank line
                .map(line -> extract(line, REGEX))    // extract uri
                .filter(line -> line != null && line.trim().length() != 0) //filter blank line
                .map(line -> {
                    if(!line.endsWith(".do") && !line.endsWith(".json") && !line.endsWith(SUFFIX)){
                        return line + SUFFIX;
                    }
                    return line;
                })   //append suffix
                .filter(uri -> !"/error.do".equals(uri))
                .filter(uri -> !uri.startsWith("/api"))
                .distinct().sorted()  //distinct and sort
                .forEach(line -> System.out.println(toSql(line)));
    }

    public static String extract(String source, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        if(matcher.find())
            return matcher.group(1);
        return null;
    }

    public static String toSql(String uri){
        return String.format("INSERT INTO `url` VALUES ('%s', '', '', '%s', '', '2017-01-20 10:53:53');", getCode(), uri);
    }

    public static String getCode(){
        String format = String.format("%s%s", preffix, StringUtils.leftPad(String.valueOf(counter), 3, "0"));
        counter ++;
        return format;
    }
}
