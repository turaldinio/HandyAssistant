import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(InetAddress.getLocalHost(), 8089)) {
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            printWriter.println("turaldinio");

            String response = reader.readLine();
            System.out.println(response);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
