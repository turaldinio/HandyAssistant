package Client;

import HelperClasses.ConsoleHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("netology.homework", 8089)) {

            ConsoleHelper.writeMessage("Соединение с сервером установлено");

            Scanner scan = new Scanner(System.in);
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (reader.ready()) {
                System.out.println(reader.readLine());
            }

            printWriter.println(scan.nextInt());


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
