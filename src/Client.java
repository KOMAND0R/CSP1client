import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    static String HOST = "localhost";
    static int PORT = 12345;
    public static void main(String[] args) throws IOException
    {
        Scanner s = new Scanner(System.in);

        System.err.println("Попытка подключения к " + HOST + " через порт " + PORT);

        Socket echoSocket = new Socket(HOST, PORT);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Ввод: ");
        String userInput = stdIn.readLine();

        while (!userInput.equals("stop"))
        {
            out.println(userInput);
            System.out.println("Ответ: " + in.readLine());
            System.out.print("Ввод: ");
            userInput = stdIn.readLine();
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
        System.err.println("Отключение от сервера");
    }
}

