package httpclient;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientUtil {

	private static CloseableHttpClient client = HttpClients.createDefault();
	
	public static CloseableHttpClient getHttpClient(){
		return client;
	}

}
