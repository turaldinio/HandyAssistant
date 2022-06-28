import HelperClasses.City;
import Internet.Request;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.IOException;

public class RequestTest {

    private Request request;

    @BeforeEach
    public void init() {
        request = new Request();
    }

    /**
     * Проверка правильности парсера, удаляющего все тэги из строки
     * Конченая строка не должна содержать латинские буквы
     * <p>
     * Проверка выбрасывания исключения при длительном ожидании ответа
     *
     * @throws IOException
     */
    @Test
    void getAdviceOfTheDay() throws IOException {
        Assertions.assertTrue(request.getAdviceOfTheDay().matches("[^a-zA-Z]*"));

    }

    @ParameterizedTest
    @EnumSource(value = City.class)
    void currentWeather(City city) throws IOException {
        Assertions.assertDoesNotThrow(() -> request.currentWeather(city.name()));
    }

    @Test
    void connectionTimeOut() {
        Assertions.assertThrows(IOException.class, () -> new Request(createRequestForException()).getAdviceOfTheDay());

    }


    public CloseableHttpClient createRequestForException() {
        return HttpClientBuilder.create()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/100.0.4896.160 YaBrowser/22.5.3.705 Yowser/2.5 Safari/537.36")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD)
                        .setRedirectsEnabled(true)
                        .setConnectTimeout(10)
                        .build())
                .build();
    }
}
