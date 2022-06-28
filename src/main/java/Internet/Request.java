package Internet;

import org.apache.http.HttpHeaders;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Request {
    private static final String weatherService = "https://world-weather.ru/pogoda/russia/";
    private static final String adviceOfTheDay = "https://www.fanfenshui.ru/kalendari/sovet-dnya.html";

    private CloseableHttpClient httpClient;

    public Request() {
        httpClient = HttpClientBuilder.create()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/100.0.4896.160 YaBrowser/22.5.3.705 Yowser/2.5 Safari/537.36")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD)
                        .setConnectTimeout(5000)
                        .setRedirectsEnabled(true)
                        .build())
                .build();
    }

    public Request(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String currentWeather(String city) throws IOException {
        HttpGet request = new HttpGet(weatherService + city);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.TEXT_HTML.getMimeType());

        CloseableHttpResponse response = httpClient.execute(request);

        String line = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);


        Document document = Jsoup.parse(line);

        return String.format("На данный момент температура воздуха в городе %s составляет %s ℃",
                city,
                document.getElementById("weather-now-number").childNodes().get(0).toString());

    }

    public String getAdviceOfTheDay() throws IOException {
        HttpGet request = new HttpGet(adviceOfTheDay);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.TEXT_HTML.getMimeType());

        CloseableHttpResponse response = httpClient.execute(request);

        String line = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        Document document = Jsoup.parse(line);

        String advice = Jsoup.parse(document.getElementsByTag("h2").toString()).text();
        String author = Jsoup.parse(document.getElementsByTag("h4").toString()).text();


        if (author.length() == 0) {
            return advice.trim();
        }

        return advice + "(" + author + ")".trim();

    }


}
