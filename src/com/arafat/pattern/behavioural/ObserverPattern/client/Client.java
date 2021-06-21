package com.arafat.pattern.behavioural.ObserverPattern.client;


import com.arafat.pattern.behavioural.ObserverPattern.StockEntity;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Observer {

    private String name;
    private int ID;
//    private Socket givenSocket;
    private DataOutputStream dos;
    private DataInputStream dis;
//    private List<StockEntity> stockList;

    //    private static DataInputStream dis;
//    static DataOutputStream dos;
    public Client(String name) {
        this.name = name;

    }


    public Client(int ID,DataOutputStream dos){
        this.dos = dos;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public Client setID(int ID) {
        this.ID = ID;
        return this;
    }



    @Override
    public void update(StockEntity stk, String msg) {
//        System.out.println("something is changed for ClientID:: " +this.ID);
//        System.out.println("Current Stock State::\n\t"+ stk.toString());

        try {
            System.out.println("writing on input stream");
            this.dos.writeUTF("**SOMETHING HAS BEEN CHANGED FOR CLIENTID:: " +this.ID+"\nINFO:: "+msg+"\nCURRENT STOCK("+stk.getName()+") STATE::\n\t"+ stk.toString().toUpperCase());
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

            System.out.println(dis.readUTF());

//            Thread t = new Thread(new DosWriter(dos));
//            t.start();

            // the following loop performs the exchange of
            // information between client and client handler
            while (true)
            {
                System.out.println("\n\t>>S: Subscribe. Example S P1\n\t>>U: Unsubscribe. Example U P1\n"+ "\tType 'M' to see your Inbox!\n "+
                        "\t>>Type 'EXIT' to terminate connection.");
//                try {
//
//                    System.out.println("i am here\n");
//                    Thread thread = new Thread(new DisReader(dis));
//                    thread.start();
//
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }

//                    System.out.println("reading...");
                   String  tosend = br.readLine();

//                String tosend = "lol";
//                System.out.println("tosend: "+ tosend);

                if(tosend.equalsIgnoreCase("Exit"))
                {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                else if (tosend.equalsIgnoreCase("M")){
                    if (dis.available()!=0){
                        System.out.println(dis.readUTF());
                    }
                    else {
                        System.out.println("No updates now!");
                    }

                }

//                Thread t2 = new Thread(new DosWriter(dos,tosend));
//                t2.start();
                else {
                    dos.writeUTF(tosend);



//                Thread thread1 = new Thread(new DisReader(dis));
//                thread1.start();

                    String received = dis.readUTF();
                    System.out.println(received);
                }
            }

            // closing resources
            br.close();
            dis.close();
            dos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

//class DisReader implements Runnable{
//
//    DataInputStream dis;
//    DisReader(DataInputStream dis){
//        this.dis = dis;
//    }
//
//    @Override
//    public void run() {
//        System.out.println("reading from dis:");
//        try {
//
//
//            String rcvd = this.dis.readUTF();
//            System.out.println(rcvd);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
//
//class DosWriter implements Runnable{
//
//    DataOutputStream dos;
//
//
//    DosWriter(DataOutputStream dos){
//        this.dos = dos;
//
//    }
//
//
//    @Override
//    public void run() {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("writing on dos:");
//        try {
//            String cmd = br.readLine();
//            this.dos.writeUTF(cmd);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
