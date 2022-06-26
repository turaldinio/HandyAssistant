package Internet;

import HelperClasses.Citys;
import HelperClasses.ConsoleHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

    public static void main(String[] args) throws IOException {
        ConsoleHelper.writeMessage("Сервер запущен");

        try (ServerSocket serverSocket = new ServerSocket(8089)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                printWriter.println("Добро пожаловать на сервер-помощник");
                printWriter.println("Чем вам помочь?");

                while (true) {

                    printWriter.println("1. Прогноз погоды");
                    printWriter.println("2. Текущая дата");
                    printWriter.println("3. Скачать картинку дня");
                    printWriter.println("Для выхода укажите любой другой номер");

                    int response = Integer.parseInt(reader.readLine());

                    switch (response) {
                        case 1:
                            currentWeather(printWriter, reader);
                            break;
                        case 2:

                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }

                }
            }

        }
    }

    public static String currentWeather(PrintWriter printWriter, BufferedReader reader) throws IOException {
        printWriter.println("Укажите город, для которого необходимо узнать погоду:");
        printWriter.println(Arrays.toString(Citys.values()).replaceAll("[-]", ""));

        Citys city = Citys.valueOf(reader.readLine());
        if (city == null) {
            ConsoleHelper.writeMessage("Погода для данного горда недоступна");
            return null;
        }
        Request.currentWeather(city);
        return null;
    }

}
