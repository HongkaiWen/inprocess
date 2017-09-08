package jsoup;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by hongkai on 2017/8/30.
 */
public class BaiduPlay {

    public static void main(String args[]) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://github.com/search?utf8=%E2%9C%93&q=photo&type=")
                .asString();
//        System.out.println(response.getBody());

//        HttpResponse<String> response = Unirest.get("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E7%BE%8E%E9%A3%9F&oq=URL%2526gt%253BN%2526lt%253BODING&rsv_pq=813949ce0001d01f&rsv_t=d7cfgLnmtArql9aoEyO7Bbx2jTGhIX3EqLS3LVgwie4olZa2WU65yIiFfWc&rqlang=cn&rsv_enter=1&inputT=18339&rsv_sug3=32&rsv_sug1=25&rsv_sug7=100&rsv_sug2=0&rsv_sug4=18339")
//                .header("cache-control", "no-cache")
//                .header("postman-token", "453c15b0-2bad-3bb9-80cf-daab02c514b4")
//                .asString();

//        HttpResponse<String> response = Unirest.get("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E7%BE%8E%E9%A3%9F&oq=jsoup%2520get%2520a%2520href&rsv_pq=b1a0c7fa00001d31&rsv_t=d0e7Cws3Z8FNb12H9vhqTcjT2mc28KqfMDbUi%2B%2BV11H4UtV%2BcHY4VlvGbRw&rqlang=cn&rsv_enter=1&inputT=7873&rsv_sug3=60&rsv_sug1=55&rsv_sug7=100&rsv_sug2=0&rsv_sug4=7873&rsv_sug=1")
//                .header("cache-control", "no-cache")
//                .header("postman-token", "9be9bb24-1af0-d942-ba38-307b60e18033")
//                .asString();
//
        Document doc = Jsoup.parse(response.getBody());

        Elements links = doc.select("a");
        links.forEach(e -> System.out.println(e.attr("href")));

    }

}
