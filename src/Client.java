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
            Scanner scan = new Scanner(System.in);
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println(reader.readLine());

            printWriter.println(scan.nextLine());

            System.out.println(reader.readLine());

            printWriter.println(scan.nextLine());

            System.out.println(reader.readLine());

            printWriter.println(scan.nextLine());

            System.out.println(reader.readLine());

            System.out.println(reader.readLine());
            System.out.println(reader.readLine());
            System.out.println(reader.readLine());


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
