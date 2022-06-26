package Server;

import HelperClasses.ConsoleHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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

                printWriter.println("1. Прогноз погоды");
                printWriter.println("2. Текущая дата");
                printWriter.println("3. Скачать картинку дня");

                int response = Integer.parseInt(reader.readLine());

            }

        }
    }

}
