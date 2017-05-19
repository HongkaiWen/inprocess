import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by hongkai on 2017/5/17.
 */
public class W {

    public static void main(String args[]){
        JSONArray result = new JSONArray();

        result.add(generateItem("如何发布应用", "进入“容器服务”模块 -> 应用管理 -> 应用申请", false, null));
        result.add(generateItem("应用无法访问内网数据库怎么办", "进入“容器服务”模块 -> 平台应用维护 -> 选择有问题的应用 -> 应用维护 -> 网络 -> 增加网络出口规则", false, null));
        result.add(generateItem("如何查看应用日志", "进入“容器服务”模块 -> 平台应用维护 -> 选择指定应用 -> 应用维护 -> 实例 -> 应用日志下载", false, null));
        result.add(generateItem("浮云网新版发布", "", true, "http://www.fuyunwang.com"));

        System.out.println(result.toJSONString());

    }

    private static JSONObject generateItem(String title, String content, boolean redirect, String url){
        JSONObject tip = new JSONObject();
        tip.put("title", title);
        tip.put("content", content);
        tip.put("redirect", redirect);
        tip.put("url", url);
        return tip;
    }
}
