class  MessagesThread extends Thread {

    private Client newClient;
    private String name;

    MessagesThread(Client newClient) {
        this.newClient = newClient;
    }

    public  void run() {
        String message;
        try {
            while(true) {
                String msg = newClient.in.readLine();
                System.out.println(msg);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
