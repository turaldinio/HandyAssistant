package Internet;

import Exceptions.CityNotFoundException;
import HelperClasses.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("Сервер запущен");

        try (ServerSocket serverSocket = new ServerSocket(8089);

             Socket clientSocket = serverSocket.accept();
             PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {

            printWriter.println("Добро пожаловать на сервер-помощник");
            printWriter.println("Чем вам помочь?");


            while (true) {

                printWriter.println("1. Прогноз погоды");
                printWriter.println("2. Получить случайный совет");
                printWriter.println("3. Скачать картинку дня");
                printWriter.println("Для выхода укажите любой другой номер");


                int response = Integer.parseInt(reader.readLine());

                switch (response) {
                    case 1:
                        try {
                            String currentWeather = currentWeather(printWriter, reader);
                            printWriter.println(currentWeather);
                        } catch (CityNotFoundException c) {
                            printWriter.println(c.getMessage());
                        }

                        break;

                    case 2:
                        String advice = getAdviceOfTheDay(printWriter);
                        printWriter.println(advice);
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }

            }
        }

    }

    public static String currentWeather(PrintWriter printWriter, BufferedReader reader) throws IOException, CityNotFoundException {
        printWriter.println("Укажите город, для которого необходимо узнать погоду:");
        printWriter.println(Arrays.toString(City.values()).replaceAll("[-]", ""));

        String line = reader.readLine();


        String city = City.getCity(line);
        if (city == null) {
            throw new CityNotFoundException("Ошибка! Проверьте корректность названия города");

        }
        return Request.currentWeather(city);


    }

    public static String getAdviceOfTheDay(PrintWriter printWriter) throws IOException {
        printWriter.println("Совет для вас:");

        return Request.getAdviceOfTheDay();
    }


}
