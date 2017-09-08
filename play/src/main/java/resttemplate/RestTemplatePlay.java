package resttemplate;

import org.apache.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hongkai on 2017/9/7.
 */
public class RestTemplatePlay {

    public static void main(String args[]){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity("http://www.baidu.com", String.class);
        String responseContentType = result.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        System.out.println(responseContentType);
        System.out.println(result);
    }

}
