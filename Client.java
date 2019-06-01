import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Serializable{

    private transient Socket clientSocket = null;
    BufferedReader in;
    private PrintWriter out;

    private void connect() {

        try {
            this.clientSocket = new Socket("localhost", 2019);

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void disconnect() {
        try {
            this.in.close();
            this.out.close();
            this.clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendName(String name){
        out.println(name);
        out.flush();
    }

    private void sendMessage(String msg) {
        out.println(msg);
        out.flush();
    }

    public static void main(String[] args) {
        Client newClient = new Client();
        MessagesThread messagesThread = new MessagesThread(newClient);

        newClient.connect();

        System.out.print("Enter a name : ");
        String name = (new Scanner(System.in)).nextLine();
        newClient.sendName(name);

        System.out.println("Welcome " + name);
        System.out.println("Start your Discution :");
        messagesThread.start();

        while (true) {
            String message = (new Scanner(System.in)).nextLine();
            newClient.sendMessage(message);
        }
    }
}