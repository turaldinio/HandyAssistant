import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.PriorityQueue;
import java.util.Queue;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("server is running");
        Server server = new Server();

        try (ServerSocket serverSocket = new ServerSocket(8089)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                printWriter.println("welcome to the registration site netology.homework. Enter your name");

                String name = reader.readLine();

                printWriter.println(String.format("Hi %s, enter your age", name));
                String age = reader.readLine();

                printWriter.println("Ok.. and finally enter your sex");
                String sex = reader.readLine();

                printWriter.println(String.format("You have successfully registered your data:" +
                        "\nName- %s\nAge- %s\nSex- %s", name, age, sex));


            }

        }
    }

}
