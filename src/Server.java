import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("server is running");
        try (ServerSocket serverSocket = new ServerSocket(8089)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                System.out.println("New connection accepted");

                final String name = reader.readLine();

                printWriter.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            }

        }
    }
}
