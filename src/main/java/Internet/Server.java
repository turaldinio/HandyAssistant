package Internet;

import Exceptions.CityNotFoundException;
import Enum.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final Request request = new Request();


    public static void main(String[] args) throws IOException {
        Server server = new Server();
        System.out.println("Сервер запущен");

        try (ServerSocket serverSocket = new ServerSocket(8089);

             Socket clientSocket = serverSocket.accept();
             PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {

            printWriter.println("Добро пожаловать на сервер-помощник");
            printWriter.println("Чем вам помочь?");


            printWriter.println("1. Прогноз погоды");
            printWriter.println("2. Получить случайный совет");
            printWriter.println("Для выхода укажите любой другой номер");


            int response = Integer.parseInt(reader.readLine());

            switch (response) {
                case 1:
                    try {
                        String currentWeather = server.currentWeather(printWriter, reader);
                        printWriter.println(currentWeather);
                    } catch (CityNotFoundException c) {
                        printWriter.println(c.getMessage());
                        printWriter.println("Check the correctness of the city name");

                    }

                    break;

                case 2:
                    try {
                        String advice = server.getAdviceOfTheDay(printWriter);
                        printWriter.println(advice);
                    } catch (IOException a) {
                        printWriter.println("Что-то пошло не так. Повторите попытку");
                    }
                    break;
                default:
                    printWriter.println("До свидания ");
                    break;
            }

        }
    }


    public String currentWeather(PrintWriter printWriter, BufferedReader reader) throws IOException, CityNotFoundException {
        printWriter.println("Укажите город, для которого необходимо узнать погоду:");

        for (int a = 0; a < City.values().length; a++) {
            printWriter.println(City.values()[a]);
        }


        String line = reader.readLine();


        String city = City.getCity(line);
        if (city == null) {
            throw new CityNotFoundException("Ошибка! Проверьте корректность названия города");

        }
        return request.currentWeather(city);


    }

    public String getAdviceOfTheDay(PrintWriter printWriter) throws IOException {
        printWriter.println("Совет для вас:");

        return request.getAdviceOfTheDay();
    }
}
