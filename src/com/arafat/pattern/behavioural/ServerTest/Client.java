package com.arafat.pattern.behavioural.ServerTest;


import com.arafat.pattern.behavioural.ServerTest.Observer;
import com.arafat.pattern.behavioural.ServerTest.StockEntity;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client extends Observer {

    private String name;
    private int ID;
    private Socket givenSocket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private List<StockEntity> stockList;

//    private static DataInputStream dis;
//    static DataOutputStream dos;
    public Client(String name) {
        this.name = name;
        stockList = new ArrayList<StockEntity>();
    }
    public Client(int id){
        this.ID = id;
    }

    public Client(int ID,Socket s){
        this.givenSocket = s;
        this.ID = ID;
    }

    public Client(int ID,DataOutputStream dos){
        this.dos = dos;
        this.ID = ID;
    }
    public  DataOutputStream getDataOs(){
        return this.dos;
    }

    public Socket getGivenSocket() {
        return givenSocket;
    }

    public Client setGivenSocket(Socket givenSocket) {
        this.givenSocket = givenSocket;
        return this;
    }

    public int getID() {
        return ID;
    }

    public Client setID(int ID) {
        this.ID = ID;
        return this;
    }

    private  DataInputStream getDis() throws IOException{
        return new DataInputStream(this.givenSocket.getInputStream());
    }
    private  DataOutputStream getDos() throws IOException{
        return new DataOutputStream(this.givenSocket.getOutputStream());
    }

    @Override
    public void update(StockEntity stk, String msg) {
        System.out.println("something is changed for ClientID:: " +this.ID);
        System.out.println("Current Stock State::\n\t"+ stk.toString());

        try {
            System.out.println("writing on input stream");
            this.dos.writeUTF("something is changed for ClientID:: " +this.ID+"\nInfo:: "+msg+"\nCurrent Stock State::\n\t"+ stk.toString());
//            System.out.println(this.getDis().readUTF());

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }



    public static void main(String[] args) throws IOException
    {
        try
        {
//            Scanner scn = new Scanner(System.in);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            /** getting localhost ip */

            InetAddress ip = InetAddress.getByName("localhost");

            /** establishing the connection with server port 5056 */

            Socket s = new Socket(ip, 5056);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // the following loop performs the exchange of
            // information between client and client handler
            while (true)
            {
                System.out.println("\n\t>>S: Subscribe. Example S P1\n\t>>U: Unsubscribe. Example U P1\n"+
                        "\t>>Type Exit to terminate connection.");
                try {

                    System.out.println("i am here\n");
                    Thread thread = new Thread(new DisReader(dis));
                    thread.start();

                }
                catch (Exception e){
                    e.printStackTrace();
                }

                System.out.println("reading...");
                String tosend = br.readLine();
                System.out.println("tosend: "+ tosend);

                if(tosend.equals("Exit"))
                {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

//                Thread t2 = new Thread(new DosWriter(dos,tosend));
//                t2.start();
                dos.writeUTF(tosend);

                // If client sends exit,close this connection
                // and then break from the while loop


//                Thread thread1 = new Thread(new DisReader(dis));
//                thread1.start();

                String received = dis.readUTF();
                System.out.println(received);
            }

            // closing resources
            br.close();
            dis.close();
            dos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
//    class CommandListener implements Runnable {
//
//        Server server;
//
//        CommandListener(int n) {
//
//        }
//
//        CommandListener(Server server) {
//            this.server = server;
//        }
//
//        private Server returnServer() {
//            return this.server;
//        }
//
//        public Server getServer() {
//            return server;
//        }
//
//        public Server.CommandListener setServer(Server server) {
//            this.server = server;
//            return this;
//        }
//
//        @Override
//        public void run() {
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            while (true) {
//                try {
//                    System.out.println("i am running");
//                    String command = br.readLine();
//                    System.out.println(command);
//                    StringTokenizer stringTokenizer = new StringTokenizer(command);
//                    String type = stringTokenizer.nextToken();
//                    String stkName = stringTokenizer.nextToken();
//                    Double value = Double.parseDouble(stringTokenizer.nextToken());
//
//
//                    this.server.addStock(new StockEntity("P5", 567.0, 3));
//
//
//                    if (type.equalsIgnoreCase("I")) {
//                        server.increasePrice(stkName, value);
//                    } else {
//                        System.out.println("Invalid input");
//                    }
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
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }




}

class DisReader implements Runnable{

    DataInputStream dis;
    DisReader(DataInputStream dis){
        this.dis = dis;
    }

    @Override
    public void run() {
        System.out.println("reading from dis:");
        try {


            String rcvd = this.dis.readUTF();
            System.out.println(rcvd);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

class DosWriter implements Runnable{

    DataOutputStream dos;
    String cmd;

    DosWriter(DataOutputStream dos, String cmd){
        this.dos = dos;
        this.cmd = cmd;
    }

    @Override
    public void run() {
        System.out.println("writing on dos:");
        try {

            dos.writeUTF(cmd);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
