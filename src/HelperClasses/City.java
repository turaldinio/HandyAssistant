package HelperClasses;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum City {
    Saint_Petersburg, Moscow, Irkutsk, Rostov_na_Dany, Novosibirsk, Izhevsk,;

    public static String getCity(String line) {
        for (City a : City.values()) {
            if (a.name().equalsIgnoreCase(line)) {
                return a.name();
            }
        }
        return null;
    }


}
