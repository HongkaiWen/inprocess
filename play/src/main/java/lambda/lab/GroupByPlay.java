package lambda.lab;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hongkai on 2017/3/22.
 */
public class GroupByPlay {

    public static void main(String args[]){
        Double fraction = 1.0/5;


        String t = "{\"code\":\"000000\",\"message\":null,\"result\":{\"fieldList\":[{\"name\":\"user\",\"needInput\":false,\"value\":\"hongkai\",\"captcha\":false},{\"name\":\"pw\",\"needInput\":false,\"value\":\"123456\",\"captcha\":false},{\"name\":\"captcha\",\"needInput\":true,\"value\":null,\"captcha\":true}],\"post\":true,\"captchaUrl\":\"http://mscx-portal.eastdc.cn:85/login/captcha.do?t=1503911745348\",\"action\":\"http://p1/login.do\"},\"status\":\"OK\"}";


        Map<Integer, List<Integer>> result = Stream.of(1, 1, 3, 4, 5).collect(Collectors.groupingBy(v -> v));

        Map<Integer, Double> result2 = Stream.of(1, 1, 3, 4, 5).collect(Collectors.groupingBy(side -> side,
                Collectors.summingDouble(p -> fraction)));

        CollectPlay.printMap(result);
        System.out.println();
        CollectPlay.printMap(result2);
    }

}
