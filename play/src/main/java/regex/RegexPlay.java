package regex;

/**
 * Created by hongkai on 2016/3/17.
 */
public class RegexPlay {
    public static void main(String args[]) throws InterruptedException {
        String email_regx = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+((,|，)[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+)*$|^$)";
        System.out.println("".matches(email_regx));
        System.out.println("java_win@yeah.net".matches(email_regx));
        System.out.println("345633444@qq.com,ddd@yeah.net".matches(email_regx));
        System.out.println("345633444@qq.com,eee@yeah.net,eeeee@yeah.net，ccccc@yeah.net".matches(email_regx));
        System.out.println("--------------------------------------------");
        String uri_regx = "^/.*$";
        System.out.println("/abc".matches(uri_regx));
        System.out.println("/".matches(uri_regx));
        System.out.println("/dd/cv".matches(uri_regx));
        System.out.println("/dd/dd/cd/ssdf.xxx".matches(uri_regx));
        System.out.println("--------------------------------------------");
        String temp = "^\\S$";
        System.out.println("\n".matches(temp));
    }
}
