package jsoup;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by hongkai on 2017/8/30.
 */
public class Play {

    public static void main(String args[]) throws UnirestException {

        HttpResponse<String> response = Unirest.get("http://gitlab.dctech.club:81/users/sign_in")
                .header("cache-control", "no-cache")
                .header("postman-token", "453c15b0-2bad-3bb9-80cf-daab02c514b4")
                .asString();

        Document doc = Jsoup.parse(response.getBody());

        System.out.println(response.getBody());

        System.out.println("----------------------------");

        String val = doc.select("input[name=authenticity_token]").val();
        System.out.println(val);
    }
}
