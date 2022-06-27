package Internet;

import HelperClasses.City;
import HelperClasses.ConsoleHelper;
import HelperClasses.ObjectStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

public class Server {

    public static boolean waitResponse = false;

    public static void main(String[] args) throws IOException {
        ConsoleHelper.writeMessage("Сервер запущен");


        new Thread(() -> {
            try {
                Thread.sleep(2000);
                try (Socket clientSocket = new Socket("netology.homework", 8089)) {

                    ConsoleHelper.writeMessage("Соединение с сервером установлено");

                    Scanner scan = new Scanner(System.in);
                    PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    while (!clientSocket.isClosed()) {

                        while (reader.ready()) {
                            System.out.println(reader.readLine());
                        }

                        if (Server.isWaitResponse()) {
                            System.out.println("пиши");
                            printWriter.println(scan.nextLine());
                        } else {
                            Thread.sleep(1000);
                        }
                        System.out.println("пош на след кург" + " " + ObjectStatus.isWaitResponse());

                    }


                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        try (ServerSocket serverSocket = new ServerSocket(8089)) {

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


                int response = Integer.parseInt(clientMessageRead(reader));


                switch (response) {
                    case 1:
                        String currentWeather = currentWeather(printWriter, reader);
                        if (currentWeather == null) {
                            System.out.println("Ошибка получения данных. Проверьте корректность введенных данных или повторите запрос");
                            break;
                        } else {
                            System.out.println(currentWeather);
                        }

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

    public static String currentWeather(PrintWriter printWriter, BufferedReader reader) throws IOException {
        printWriter.println("Укажите город, для которого необходимо узнать погоду:");
        printWriter.println(Arrays.toString(City.values()).replaceAll("[-]", ""));

        String line = clientMessageRead(reader);

        String city = City.getCity(line);
        if (city == null) {
            return null;

        }
        return Request.currentWeather(city);


    }

    public static String clientMessageRead(BufferedReader reader) {
        waitResponse = true;
        try {
            String line = reader.readLine();
            waitResponse = false;
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isWaitResponse() {
        return waitResponse;
    }


}
