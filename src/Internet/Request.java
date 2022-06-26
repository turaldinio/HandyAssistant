package Internet;

import HelperClasses.Citys;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class Request {
    private static final String weatherService = "https://yandex.ru/pogoda/";

    private static CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setUserAgent("Test service")
            .setDefaultRequestConfig(RequestConfig.custom()
                    .setConnectTimeout(3000)
                    .setSocketTimeout(30000)
                    .setRedirectsEnabled(false)
                    .build())
            .build();


    public static void currentWeather(Citys citys) throws IOException {
        HttpGet request = new HttpGet(weatherService + citys);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = httpClient.execute(request);

    }


}
