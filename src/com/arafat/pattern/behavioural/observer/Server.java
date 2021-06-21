//package com.arafat.pattern.behavioural.observer;
//
//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//public class Server {
//
//    private List<Observer> observerList;
//    private HashMap<StockEntity, ArrayList<Observer>> clientList;
//    private ConcurrentHashMap<StockEntity, ArrayList<Observer>> stockClientMap;
//    private List<StockEntity> stockList;
//    private String title;
//
//
//    public Server(String title) {
//        this.observerList = new ArrayList<Observer>();
//        this.stockList = new ArrayList<StockEntity>();
//        this.clientList = new HashMap<StockEntity, ArrayList<Observer>>();
//        this.stockClientMap = new ConcurrentHashMap<StockEntity, ArrayList<Observer>>();
//        this.title = title;
//    }
//
//
//    public void  setState(String stkName, DataOutputStream dos){
//        for (Observer observer: getObserverListbyStockName(stkName)
//             ) {
//            this.Notify(observer,stkName);
//
//        }
//    }
//
//    public HashMap<StockEntity, ArrayList<Observer>> getClientList() {
//        return clientList;
//    }
//
//    public void  setState(String stkName){
//        for (Observer observer: getObserverListbyStockName(stkName)
//        ) {
//            this.Notify(observer,stkName);
//
//        }
//    }
//
//    public void subsCribe(Observer observer, String stockName) {
//
//        //observerList.add(observer);
//
//        Iterator<Map.Entry<StockEntity, ArrayList<Observer>>>
//                iterator = clientList.entrySet().iterator();
//
//        while (iterator.hasNext()) {
//            Map.Entry<StockEntity, ArrayList<Observer>>
//                    entry
//                    = iterator.next();
//
//            if (stockName.equalsIgnoreCase(entry.getKey().getName())) {
//
//                entry.getValue().add(observer);
//            }
//        }
//       // Notify(observer, stockName);
//    }
//
//
//    public void subsCribe(int ID, String stockName,DataOutputStream dos) {
//
//        //observerList.add(observer);
//        Observer observer = new Client(ID);
//
//        Iterator<Map.Entry<StockEntity, ArrayList<Observer>>>
//                iterator = clientList.entrySet().iterator();
//
//        while (iterator.hasNext()) {
//            Map.Entry<StockEntity, ArrayList<Observer>>
//                    entry
//                    = iterator.next();
//
//            if (stockName.equalsIgnoreCase(entry.getKey().getName())) {
//
//                entry.getValue().add(observer);
//            }
//        }
//        // Notify(observer, stockName);
//    }
//
//
//
//    public void unSubscribe(Observer observer, String stockName) {
//
//        Iterator<Map.Entry<StockEntity, ArrayList<Observer>>>
//                iterator = clientList.entrySet().iterator();
//
//        while (iterator.hasNext()) {
//            Map.Entry<StockEntity, ArrayList<Observer>>
//                    entry
//                    = iterator.next();
//
//            if (stockName.equalsIgnoreCase(entry.getKey().getName())) {
//
//                entry.getValue().remove(observer);
//            }
//        }
//        //this.setState(stockName);
//    }
//
//    public void unSubscribe(Observer observer, String stockName, DataOutputStream dos) {
//
//        Iterator<Map.Entry<StockEntity, ArrayList<Observer>>>
//                iterator = clientList.entrySet().iterator();
//
//        while (iterator.hasNext()) {
//            Map.Entry<StockEntity, ArrayList<Observer>>
//                    entry
//                    = iterator.next();
//
//            if (stockName.equalsIgnoreCase(entry.getKey().getName())) {
//
//                entry.getValue().remove(observer);
//            }
//        }
//        //this.setState(stockName);
//    }
//
//
//    private void Notify(Observer observer,String stkName) {
//        observer.update(this.getStockEntitybyStockName(stkName));
//    }
//
//    public void decreasePrice(String stkName, double decreaseBy) {
//
//        this.getStockEntitybyStockName(stkName).decreasePrice(decreaseBy);
//        this.setState(stkName);
//
//    }
//
//    public void increasePrice(String stkName, double increaseBy) {
//        this.getStockEntitybyStockName(stkName).increasePrice(increaseBy);
//
//        this.setState(stkName);
//    }
//
//
//
//    public  void changeCount(String stkName, int count, DataOutputStream dos){
//        this.getStockEntitybyStockName(stkName).setCount(count);
//
//        this.setState(stkName, dos);
//    }
//
//    /***
//     * Helper methods
//     * @param stkName
//     * @return
//     */
//
//    public ArrayList<Observer> getObserverListbyStockName(String stkName) {
//        Iterator<Map.Entry<StockEntity, ArrayList<Observer>>>
//                iterator = clientList.entrySet().iterator();
//
//        while (iterator.hasNext()) {
//            Map.Entry<StockEntity, ArrayList<Observer>>
//                    entry
//                    = iterator.next();
//
//            if (stkName.equalsIgnoreCase(entry.getKey().getName())) {
//
//                return entry.getValue();
//            }
//        }
//        return null;
//
//
//    }
//
//
//
//    public StockEntity getStockEntitybyStockName(String stkName) {
//        Iterator<Map.Entry<StockEntity, ArrayList<Observer>>>
//                iterator = clientList.entrySet().iterator();
//
//        while (iterator.hasNext()) {
//            Map.Entry<StockEntity, ArrayList<Observer>>
//                    entry
//                    = iterator.next();
//
//            if (stkName.equalsIgnoreCase(entry.getKey().getName())) {
//
//                return entry.getKey();
//            }
//        }
//        return null;
//
//
//    }
//
//    public void addStock(StockEntity stockEntity){
//        this.clientList.put(stockEntity,new ArrayList<Observer>());
//        System.out.println("\n\tstock added....");
//    }
//
//
//    private  void ExecuteCommand(String type, String stkName, String price){
//
//        if (type.equalsIgnoreCase("I")){
//            this.increasePrice(stkName,Double.parseDouble(price));
//        }
//
//    }
//
//
//    public static void main(String[] args) throws IOException
//    {
//        // server is listening on port 5056
//        ServerSocket ss = new ServerSocket(5056);
//        Server server = new Server("myServer");
//
//        server.addStock(new StockEntity("P1",200.0,4));
//        server.addStock(new StockEntity("P2",300.0,5));
//        server.addStock(new StockEntity("P3",400.0,2));
//
////        File file = new File("E:\\1705020\\Behavioural-Design-Pattern\\src\\com\\arafat\\pattern\\behavioural\\observer\\com.txt");
////
////        BufferedReader br = new BufferedReader(new FileReader(file));
//
//        String st;
//
//        int num =10;
////        System.out.println("before: "+num);
//        System.out.println("before: ");
//
//        for ( Map.Entry<StockEntity, ArrayList<Observer>>
//                entry: server.getClientList().entrySet()
//             ) {
//            System.out.println(entry.getKey().toString());
//
//        }
////        CommandListener commandListener = new CommandListener(server);
////        Thread commandListenerThread = new Thread(commandListener);
////
////
////        commandListenerThread.start();
//
//
//        // running infinite loop for getting
//        // client request
//        while (true)
//        {
//            System.out.println("\n\tI: Increase a stock price. Example: I P1 10.00\n" +
//                    "\tD: Decrease a stock price. Example: D P4 5.00\n" +
//                    "\tC: Change in stock count (always positive). Example: C P2 2\n" +
//                    "Subscribers will be notified if the state of their subscribed stocks has changed.\n\n");
//
//            StringTokenizer command;
//            Socket s = null;
//            System.out.println("now?");
//
//            try
//            {
//
////                server =  commandListener.getServer();
//
//
//
//                // socket object to receive incoming client requests
////                System.out.println("akhn?");
////                System.out.println("after: ");
//
////                for ( Map.Entry<StockEntity, ArrayList<Observer>>
////                        entry: server.getClientList().entrySet()
////                ) {
////                    System.out.println(entry.getKey().toString());
////
////                }
//
//                s = ss.accept();
//
////                System.out.println("kokhn?");
//                System.out.println("A new client is connected : " + s);
//
//                // obtaining input and out streams
//                DataInputStream dis = new DataInputStream(s.getInputStream());
//                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
//
//
//                // create a new thread object
//                Thread t = new ClientHandler(s, dis, dos, server);
//
//                // Invoking the start() method
//                t.start();
//
//            }
//
//            catch (Exception e){
//                s.close();
//                e.printStackTrace();
//            }
//
////            System.out.println(br.readLine());
//
////            Scanner scanner = new Scanner(System.in);
////            String comd = scanner.nextLine();
////            System.out.println(comd);
////            command = new StringTokenizer(comd);
////            String instructionType = command.nextToken();
////            String stkName = command.nextToken();
////            String price= command.nextToken();
//////
////            System.out.println("command is: "+ instructionType+" "+stkName+" "+price);
//
//        }
//    }
//}
//
//class CommandListener implements Runnable {
//
//    Server server;
//    CommandListener(int n){
//
//    }
//    CommandListener(Server server){
//        this.server = server;
//    }
//
//    private  Server returnServer(){
//        return this.server;
//    }
//
//    public Server getServer() {
//        return server;
//    }
//
//    public CommandListener setServer(Server server) {
//        this.server = server;
//        return this;
//    }
//
//    @Override
//    public void run() {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        while(true) {
//            try {
//                System.out.println("i am running");
//                String command = br.readLine();
//                System.out.println(command);
//                StringTokenizer stringTokenizer = new StringTokenizer(command);
//                String type = stringTokenizer.nextToken();
//                String stkName = stringTokenizer.nextToken();
//                Double value = Double.parseDouble(stringTokenizer.nextToken());
//
//
//                this.server.addStock(new StockEntity("P5",567.0,3));
//
//
//                if (type.equalsIgnoreCase("I")){
//                    server.increasePrice(stkName,value);
//                }
//
//                else {
//                    System.out.println("Invalid input");
//                }
//
////                if(command.equals("listClients")) {
////
////                    // Assuming you will have static list of customer. In which you will
////                    // add a customer/client when a new client get connected and remove
////                    // when a client get disconnected
////
////                    System.out.println("Total Connected customer :" + customers.size());
////                    System.out.println("Details :");
////                    for(Customer cust : customers) {
////                        System.out.println(cust.getName());
////                    }
////                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
//
//
//class ClientHandler extends Thread
//{
//
//    final DataInputStream dis;
//    final DataOutputStream dos;
//    final Socket s;
//    private Server server;
//    private  int clientID;
//
//
//    // Constructor
//    protected ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, Server servr)
//    {
//        this.s = s;
//        this.dis = dis;
//        this.dos = dos;
//        this.server =servr;
//
//        this.clientID = this.s.getPort();
//        /**  set the client id*/
//
////        this.clientID =  (int)Math.floor(Math.random()*(max-min+1)+min)
//    }
//
//    @Override
//    public void run()
//    {
//        String received;
//        String toreturn;
//
//        String stkName="";
//        String command;
//        double value=0.0;
//        CommandListener commandListener = new CommandListener(server);
//        Thread commandListenerThread = new Thread(commandListener);
//
//
//        commandListenerThread.start();
//
//
//
//        try {
//
//
//            dos.writeUTF("Your ID is: " + clientID);
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        while (true)
//        {
//            try {
//
//                // Ask user what he wants
//
////                dos.writeUTF("S: Subscribe. Example S P1\nU: Unsubscribe. Example U P1\n"+
////                        "Type Exit to terminate connection.");
//
//                // receive the answer from client
//                received = dis.readUTF();
//                StringTokenizer defaultTokenizer = new StringTokenizer(received);
//                command = defaultTokenizer.nextToken();
//
//                if (defaultTokenizer.hasMoreTokens())
//                  stkName = defaultTokenizer.nextToken();
//
//
//
//                if(received.equalsIgnoreCase("Exit"))
//                {
//                    System.out.println("Client " + this.clientID + " sends exit...");
//                    System.out.println("Closing this connection.");
//                    this.s.close();
//                    System.out.println("Connection closed");
//                    break;
//                }
//
//
//
//
//                // write on output stream based on the
//                // answer from the client
//                if ("S".equals(command)) {
//                   // server.increasePrice(stkName,value);
//                    server.subsCribe(s.getPort(),stkName,dos);
//
//                    dos.writeUTF("Subscribed");
//
//                } else if ("U".equals(command)) {
//
//                   // server.decreasePrice(stkName,value);
//
//                    dos.writeUTF("Unsubscribed");
//
//                } else{
//
//                    dos.writeUTF("Invalid input");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        try
//        {
//            // closing resources
//            this.dis.close();
//            this.dos.close();
//
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//}
//
