package HelperClasses;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum City {
    Saint_Petersburg, Moscow, Irkutsk, Rostov_na_Donu, Novosibirsk, Izhevsk, Anapa, Arkhangelsk, Astrakhan,
    Barnaul, Belgorod, Vladivostok, Volgograd, Yekaterinburg, Kazan, Kaliningrad, Kemerovo, Krasnodar,
    Krasnoyarsk, Naberezhnye_chelny, Omsk, Samara, Saratov, Sochi, Tolyatti, Tomsk, Tyumen, Ufa, Khabarovsk,
    Chelyabinsk, Yalta_1;

    public static String getCity(String line) {
        for (City a : City.values()) {
            if (a.name().equalsIgnoreCase(line)) {
                return a.name();
            }
        }
        return null;
    }


}
