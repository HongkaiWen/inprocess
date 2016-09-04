package httpclient;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class HttpSender {

    private static Logger logger = LoggerFactory.getLogger(HttpSender.class);

    public static String post(String url, int httpConnectionTimeout, List<NameValuePair> params, Header[] headers, String encoding){
        CloseableHttpResponse response = null;
        HttpPost httpPost = null;
        //默认参数
        UrlEncodedFormEntity uefEntity;
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();

            httpPost = new HttpPost(url);

            uefEntity = new UrlEncodedFormEntity(params, StringUtils.isEmpty(encoding)?"utf-8":encoding);
            httpPost.setEntity(uefEntity);

            //设置http header信息
            if(headers != null && headers.length != 0){
                httpPost.setHeaders(headers);
            }

            // 设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(httpConnectionTimeout)
                    .setConnectTimeout(httpConnectionTimeout)
                    .build();
            httpPost.setConfig(requestConfig);

            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        } catch (ConnectTimeoutException e) {
            logger.error("http connection time out", e);
            throw new RuntimeException("连接超时");
        } catch (Exception e) {
            logger.error("请求发生错误", e);
            throw new RuntimeException("请求发生错误", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.warn("close response error", e);
            }
            try {
                if (httpPost != null) {
                    httpPost.releaseConnection();
                }
            } catch (Exception e) {
                logger.warn("release http connection error", e);
            }
        }
    }

    public static String get(String url, Header[] headers){
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        HttpGet httpget = null;
        CloseableHttpResponse response = null;
        try {
            httpget = new HttpGet(url);

            //设置http header信息
            if(headers != null && headers.length != 0){
                httpget.setHeaders(headers);
            }

            response = httpClient.execute(httpget);
            return EntityUtils.toString(response.getEntity());
        } catch (ConnectTimeoutException e) {
            logger.error("http connection time out", e);
            throw new RuntimeException("连接超时");
        }  catch(Exception e){
            logger.error("请求发生错误", e);
            throw new RuntimeException("请求发生错误", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.warn("close response error", e);
            }
            try {
                if (httpget != null) {
                    httpget.releaseConnection();
                }
            } catch (Exception e) {
                logger.warn("release http connection error", e);
            }
        }
    }
}
