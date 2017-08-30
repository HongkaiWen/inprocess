package jsoup;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by player on 2017/8/30.
 */
public class JobsPlay {

    public static void main(String args[]) throws UnirestException, IOException {
        HttpResponse<InputStream> response = Unirest.get("http://search.51job.com/list/070300,000000,0000,00,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=")
                .header("cache-control", "no-cache")
                .header("Accept-Language", "zh-CN,zh;q=0.8")
                .header("contentType", "text/html; charset=utf-8")
                .header("postman-token", "6bbb8884-12e0-1613-61c1-61d538ce89b4")
                .asBinary();

        InputStream body = response.getBody();
        byte[] data = new byte[body.available()];
        body.read(data);
        String bodyText = new String(data, "gbk");

//        bodyText="<html><body><div class=e1></div></body></html>";

        Document doc = Jsoup.parse(bodyText);
        Elements select = doc.select("div[class=el]");
        select.forEach(element -> {
            element.children().forEach(e -> {
                String job = "d";
                String company = element.select("span[class=t2]").text();
                String city = element.select("span[class=t3]").text();
                String salary = element.select("span[class=t4]").text();
                System.out.println(String.format("%s %s %s %s", job, company, salary, city));
            });

        });


    }
}
