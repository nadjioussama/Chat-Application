import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server{

    private ServerSocket serverSocket = null;
    private static CopyOnWriteArrayList<ClientMessage> clientList = new CopyOnWriteArrayList<>();


    static CopyOnWriteArrayList<ClientMessage> getClientList() {
        return clientList;
    }
    static void setClientList(CopyOnWriteArrayList<ClientMessage> clientList) { Server.clientList = clientList; }

    private void startServer() {
        try {

            this.serverSocket = new ServerSocket(2019);
            System.out.println("Server On");
            int i = 0;
            while (true) {
                ClientHandler newClient = new ClientHandler(this.serverSocket.accept());
                clientList.add(new ClientMessage("", null, newClient));
                newClient.start();
                i++;

            }
        } catch (IOException e) {
            e.printStackTrace();
            stopServer();
        }
    }

    private void stopServer() {
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void broadcast(String name, String message) {
        for (ClientMessage client : clientList)
            ((ClientHandler) client.getAssosiatedThread()).out.println(name + message);
    }


    public static void main(String[] args) {
        Server chatServer = new Server();
        chatServer.startServer();
    }
}