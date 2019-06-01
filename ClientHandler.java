import java.io.*;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;


public class ClientHandler extends Thread{

    private CopyOnWriteArrayList<ClientMessage> clientList = Server.getClientList();
    private BufferedReader in;
    PrintWriter out;

    ClientHandler(Socket socket) throws IOException  {
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    @Override
    public  void run() {
        String msg,name;
        try{

            name = in.readLine();
            Server.broadcast(name, " has joined the discussion" );
            System.out.println(name + " has joined the discussion");
            System.out.println("welcome " + name);

            for (ClientMessage client : clientList)
                if (client.getAssosiatedThread() == currentThread()) {
                    client.setName(name);
                    while ((msg = in.readLine()) != null) {
                        client.setMessage(msg);
                        Server.broadcast(client.getName() + " : ", client.getMessage());
                        System.out.println(name + " : " + msg);
                    }
                }
        }
     catch (IOException e) {
            e.printStackTrace();
        }
    }
}