package HelperClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            writeMessage("Ошибка при чтении строки");
        }
        return null;
    }

    public static int readInt() {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            writeMessage("ошибка при чтении числа");
        }
        return 0;
    }
}
