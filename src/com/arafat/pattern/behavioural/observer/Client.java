//package com.arafat.pattern.behavioural.observer;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Client extends Observer{
//
//    String name;
//    int ID;
//    List<StockEntity> stockList;
//
//    public Client(String name) {
//        this.name = name;
//        stockList = new ArrayList<StockEntity>();
//    }
//    public Client(int id){
//        this.ID = id;
//    }
//
//    public int getID() {
//        return ID;
//    }
//
//    public Client setID(int ID) {
//        this.ID = ID;
//        return this;
//    }
//
//    @Override
//    public void update(StockEntity stk) {
//        System.out.println("something is changed for ::" +this.name);
//        System.out.println("Current Stock State::\n\t"+ stk.toString());
//    }
//
//
//
//    public static void main(String[] args) throws IOException
//    {
//        try
//        {
//            Scanner scn = new Scanner(System.in);
//
//            /** getting localhost ip */
//
//            InetAddress ip = InetAddress.getByName("localhost");
//
//            /** establishing the connection with server port 5056 */
//
//            Socket s = new Socket(ip, 5056);
//
//            // obtaining input and out streams
//            DataInputStream dis = new DataInputStream(s.getInputStream());
//            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
//
//            // the following loop performs the exchange of
//            // information between client and client handler
//            while (true)
//            {
//                System.out.println("S: Subscribe. Example S P1\nU: Unsubscribe. Example U P1\n"+
//                        "Type Exit to terminate connection.");
//                try {
//
//
//                    System.out.println(dis.readUTF());
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//
//                String tosend = scn.nextLine();
//
//                dos.writeUTF(tosend);
//
//                // If client sends exit,close this connection
//                // and then break from the while loop
//                if(tosend.equals("Exit"))
//                {
//                    System.out.println("Closing this connection : " + s);
//                    s.close();
//                    System.out.println("Connection closed");
//                    break;
//                }
//
//                String received = dis.readUTF();
//                System.out.println(received);
//            }
//
//            // closing resources
//            scn.close();
//            dis.close();
//            dos.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//}
