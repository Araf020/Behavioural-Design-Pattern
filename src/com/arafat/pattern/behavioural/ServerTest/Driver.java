//package com.arafat.pattern.behavioural.ServerTest;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Map;
//import java.util.StringTokenizer;
//
//public class Driver {
//
//    public static void main(String[] args) throws IOException {
//        // server is listening on port 5056
//        ServerSocket ss = new ServerSocket(5056);
//        Server server = new Server("myServer");
//
//        server.addStock(new StockEntity("P1", 200.0, 4));
//        server.addStock(new StockEntity("P2", 300.0, 5));
//        server.addStock(new StockEntity("P3", 400.0, 2));
//
////        File file = new File("E:\\1705020\\Behavioural-Design-Pattern\\src\\com\\arafat\\pattern\\behavioural\\observer\\com.txt");
////
////        BufferedReader br = new BufferedReader(new FileReader(file));
//
//        String st;
//
//        int num = 10;
////        System.out.println("before: "+num);
//        System.out.println("before: ");
//
//        for (Map.Entry<StockEntity, ArrayList<Observer>>
//                entry : server.getClientList().entrySet()
//        ) {
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
//        while (true) {
//            System.out.println("\n\tI: Increase a stock price. Example: I P1 10.00\n" +
//                    "\tD: Decrease a stock price. Example: D P4 5.00\n" +
//                    "\tC: Change in stock count (always positive). Example: C P2 2\n" +
//                    "Subscribers will be notified if the state of their subscribed stocks has changed.\n\n");
//
//            StringTokenizer command;
//            Socket s = null;
//            System.out.println("now?");
//
//            try {
//
////                server =  commandListener.getServer();
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
//                Thread t = new Server.ClientHandler(s, dis, dos, server);
//
//                // Invoking the start() method
//                t.start();
//
//            } catch (Exception e) {
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
