import Internet.Request;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.Asserts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RequestTest {
    private static final String weatherService = "https://world-weather.ru/pogoda/russia/";
    private static final String adviceOfTheDay = "https://www.fanfenshui.ru/kalendari/sovet-dnya.html";

    private static CloseableHttpClient httpClient;

    @BeforeEach
    public void init() {
        httpClient = HttpClientBuilder.create()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/100.0.4896.160 YaBrowser/22.5.3.705 Yowser/2.5 Safari/537.36")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD)
                        .setRedirectsEnabled(true)
                        .build())
                .build();
    }

    /**
     * Проверка правильности парсера, удаляющего все тэги из строки
     * Конченая строка не должна содержать латинские буквы
     *
     * @throws IOException
     */
    @Test
    void getAdviceOfTheDay() throws IOException {
        Assertions.assertTrue(Request.getAdviceOfTheDay().matches("[^a-zA-Z]*"));

        Assertions.assertThrows(IOException.class, () ->
                );
    }
}
