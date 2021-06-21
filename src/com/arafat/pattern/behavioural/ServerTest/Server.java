package com.arafat.pattern.behavioural.ServerTest;



import com.arafat.pattern.behavioural.ServerTest.Client;
import com.arafat.pattern.behavioural.ServerTest.Observer;
import com.arafat.pattern.behavioural.ServerTest.StockEntity;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Server {

    private List<Observer> observerList;
    private HashMap<StockEntity, ArrayList<Observer>> clientList;
    private ConcurrentHashMap<StockEntity, ArrayList<Observer>> stockClientMap;
    private List<StockEntity> stockList;
    private String title;
    private Server MainServer;
    // private static Server Mainserver;

    public Server(String title) {
        this.observerList = new ArrayList<Observer>();
        this.stockList = new ArrayList<StockEntity>();
        this.clientList = new HashMap<StockEntity, ArrayList<Observer>>();
        this.stockClientMap = new ConcurrentHashMap<StockEntity, ArrayList<Observer>>();
        this.title = title;
    }



    public void setState(String stkName,String msg) {
        for (Observer observer : getObserverListbyStockName(stkName)
        ) {
            this.Notify(observer, stkName,msg);

        }
    }


    public HashMap<StockEntity, ArrayList<Observer>> getClientList() {
        return clientList;
    }



    public void subsCribe(Observer observer, String stockName, DataOutputStream dos) {

        //observerList.add(observer);

        Iterator<Map.Entry<StockEntity, ArrayList<Observer>>>
                iterator = clientList.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<StockEntity, ArrayList<Observer>>
                    entry
                    = iterator.next();

            if (stockName.equalsIgnoreCase(entry.getKey().getName())) {

                entry.getValue().add(observer);
            }
        }

        this.showList();
        // Notify(observer, stockName);
    }




    public void unSubscribe(int ID, String stockName) {

        Iterator<Map.Entry<StockEntity, ArrayList<Observer>>>
                iterator = clientList.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<StockEntity, ArrayList<Observer>>
                    entry
                    = iterator.next();

            if (stockName.equalsIgnoreCase(entry.getKey().getName())) {

               ArrayList<Observer> observers =  entry.getValue();

               /** remove from Hashmap*/

               entry.getValue().remove(this.getObserverByID(ID,observers));

            }
        }
        this.showList();
        //this.setState(stockName);
    }

    private  Observer getObserverByID(int ID, ArrayList<Observer> observers){

        for (Observer obs: observers
             ) {
            if (obs.getID() == ID){
                return obs;
            }

        }
        return null;
    }


    private void Notify(Observer observer, String stkName, String msg){
        observer.update(this.getStockEntitybyStockName(stkName),msg);
    }


    public void decreasePrice(String stkName, double decreaseBy) {

        this.getStockEntitybyStockName(stkName).decreasePrice(decreaseBy);
        this.setState(stkName,"Stock Price DecreasedBy: "+ decreaseBy);

    }

    public void increasePrice(String stkName, double increaseBy) {

        this.getStockEntitybyStockName(stkName).increasePrice(increaseBy);

        this.setState(stkName,"Stock price IncreasedBy: "+increaseBy);
    }



    public void changeCount(String stkName, int count) {
        this.getStockEntitybyStockName(stkName).setCount(count);

        this.setState(stkName,"Count Changed to: "+ count);
    }

    /***
     * Helper methods
     * @param stkName
     * @return
     */

    public ArrayList<Observer> getObserverListbyStockName(String stkName) {
        Iterator<Map.Entry<StockEntity, ArrayList<Observer>>>
                iterator = clientList.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<StockEntity, ArrayList<Observer>>
                    entry
                    = iterator.next();

            if (stkName.equalsIgnoreCase(entry.getKey().getName())) {

                return entry.getValue();
            }
        }
        return null;


    }


    public StockEntity getStockEntitybyStockName(String stkName) {
        Iterator<Map.Entry<StockEntity, ArrayList<Observer>>>
                iterator = clientList.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<StockEntity, ArrayList<Observer>>
                    entry
                    = iterator.next();

            if (stkName.equalsIgnoreCase(entry.getKey().getName())) {

                return entry.getKey();
            }
        }
        return null;


    }

    public void addStock(StockEntity stockEntity) {
        this.clientList.put(stockEntity, new ArrayList<Observer>());
        System.out.println("\n\tstock added....");
    }


    private void ExecuteCommand(String type, String stkName, String price) {

        if (type.equalsIgnoreCase("I")) {
            this.increasePrice(stkName, Double.parseDouble(price));
        }

    }


    /** this to reflect any change in serverInstance to all threads*/

//    final ServerInstance serverInstance = new ServerInstance();


    class CommandListener implements Runnable {

        Server server;

        CommandListener(int n) {

        }

        CommandListener(Server server) {
            this.server = server;
        }

        private Server returnServer() {
            return this.server;
        }

        public Server getServer() {
            return server;
        }

        public CommandListener setServer(Server server) {
            this.server = server;
            return this;
        }

        @Override
        public void run() {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {

                    String command = br.readLine();
                    System.out.println(command);

                    if (command.equalsIgnoreCase("show")){
                        MainServer.showList();
                        continue;
                    }

                    StringTokenizer stringTokenizer = new StringTokenizer(command);
                    String type = stringTokenizer.nextToken();
                    String stkName = stringTokenizer.nextToken();
                    String value = stringTokenizer.nextToken();

//                    this.server.addStock(new StockEntity("P5", 567.0, 3));

                    if (type.equalsIgnoreCase("I")) {
                        MainServer.increasePrice(stkName, Double.parseDouble(value));

                    }
                    else if (type.equalsIgnoreCase("D")){
                        MainServer.decreasePrice(stkName,Double.parseDouble(value));
                    }
                    else if (type.equalsIgnoreCase("C")){
                        MainServer.changeCount(stkName,Integer.parseInt(value));
                    }


                    else {
                        System.out.println("Invalid input");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    class ClientHandler extends Thread {

        final DataInputStream dis;
        final DataOutputStream dos;
        final Socket s;
        private Server server;
        private int clientID;


        protected ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
            this.s = s;
            this.dis = dis;
            this.dos = dos;
            this.clientID = this.s.getPort();
            /**  set the client id*/

//        this.clientID =  (int)Math.floor(Math.random()*(max-min+1)+min)
        }
        // Constructor
        protected ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, Server servr) {
            this.s = s;
            this.dis = dis;
            this.dos = dos;
            this.server = servr;

            this.clientID = this.s.getPort();
            /**  set the client id*/

//        this.clientID =  (int)Math.floor(Math.random()*(max-min+1)+min)
        }

        @Override
        public void run() {
            String received;
            String toreturn;

            String stkName = "";
            String command;
            double value = 0.0;



            try {


                dos.writeUTF("Your ID is: " + clientID);

            } catch (Exception e) {
                e.printStackTrace();
            }


            while (true) {
                try {

                    // receive the answer from client
                    received = dis.readUTF();
                    StringTokenizer defaultTokenizer = new StringTokenizer(received);
                    command = defaultTokenizer.nextToken();

                    if (defaultTokenizer.hasMoreTokens())
                        stkName = defaultTokenizer.nextToken();


                    if (received.equalsIgnoreCase("Exit")) {
                        System.out.println("Client " + this.clientID + " sends exit...");
                        System.out.println("Closing this connection.");
                        this.s.close();
                        System.out.println("Connection closed");
                        break;
                    }


                    // write on output stream based on the
                    // answer from the client
                    if ("S".equals(command)) {

                        MainServer.subsCribe(new Client(s.getPort(),dos),stkName,dos);

//                        MainServer.subsCribe(new Client(s.getPort(),s),stkName, dos);

                        // server.increasePrice(stkName,value);
//                        server.subsCribe(s.getPort(), stkName, dos);

                        dos.writeUTF("\nYOU ARE SUBSCRIBED TO STOCK: "+ stkName);

                    } else if ("U".equals(command)) {

                        // server.decreasePrice(stkName,value);
                        MainServer.unSubscribe(s.getPort(),stkName);

                        dos.writeUTF("You are Unsubscribed to stock: "+ stkName);

                    } else {

                        dos.writeUTF("Invalid input");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                // closing resources
                this.dis.close();
                this.dos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



private void startServer(ServerSocket ss, Server srvr) throws IOException{
        MainServer = srvr;

        MainServer.startCommandListener();
    while (true) {
        System.out.println("\n\t>>I: Increase a stock price. Example: I P1 10.00\n" +
                "\t>>D: Decrease a stock price. Example: D P4 5.00\n" +
                "\t>>C: Change in stock count (always positive). Example: C P2 2\n" +
                "\t\n>>Type 'show' to see stocks and corresponding subscribers\n" +
                "**Subscribers will be notified if the state of their subscribed stocks has changed.\n\n");

        StringTokenizer command;
        Socket s = null;
        System.out.println("now?");

        try {

//                server =  commandListener.getServer();


            // socket object to receive incoming client requests
//                System.out.println("akhn?");
//                System.out.println("after: ");

//                for ( Map.Entry<StockEntity, ArrayList<Observer>>
//                        entry: server.getClientList().entrySet()
//                ) {
//                    System.out.println(entry.getKey().toString());
//
//                }

            s = ss.accept();

//                System.out.println("kokhn?");
            System.out.println("A new client is connected : " + s);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            this.MainServer.startClientHandlerThread(s,dis,dos);


            // create a new thread object
//              Thread t = new Server.ClientHandler(s, dis, dos, server);
//
//              // Invoking the start() method
//              t.start();

        } catch (Exception e) {
            s.close();
            e.printStackTrace();
        }

    }
}

private void showList(){

        Iterator<Map.Entry<StockEntity, ArrayList<Observer>>>
            iterator = clientList.entrySet().iterator();

    System.out.println("StockName:: ClientID");
    while (iterator.hasNext()) {
        Map.Entry<StockEntity, ArrayList<Observer>>
                entry
                = iterator.next();


        System.out.print(entry.getKey().getName()+ ":: ");
        for (Observer obs: entry.getValue()
             ) {


            System.out.print(obs.getID());
            System.out.print(", ");
        }
        System.out.println();

    }

}
private void startClientHandlerThread(Socket s, DataInputStream dis, DataOutputStream dos){

        new ClientHandler(s,dis,dos).start();
}

private void startCommandListener(){

    CommandListener commandListener = new CommandListener(MainServer);
    Thread commandListenerThread = new Thread(commandListener);
    commandListenerThread.start();
}


  public static void main(String[] args) throws IOException {


      ServerSocket ss = new ServerSocket(5056);
      Server server = new Server("myServer");



      server.addStock(new StockEntity("P1", 200.0, 4));
      server.addStock(new StockEntity("P2", 300.0, 5));
      server.addStock(new StockEntity("P3", 400.0, 2));


      /**

       * ###########===============start the server================############
       */

      server.startServer(ss,server);

  }

}
