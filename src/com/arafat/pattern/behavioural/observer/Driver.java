//package com.arafat.pattern.behavioural.observer;
//
//import java.util.Scanner;
//
//public class Driver {
//    public static void main(String[] args) {
//        StockEntity stockEntity1 = new StockEntity("p1",100,3);
//        StockEntity stockEntity2 = new StockEntity("p2",150,5);
//        StockEntity stockEntity3 = new StockEntity("p3",10,2);
//
//
//        Server ss = new Server("server1");
//        ss.addStock(stockEntity1);
//        ss.addStock(stockEntity2);
//        ss.addStock(stockEntity3);
//
//        Observer observer1 = new Client("client1");
//        Observer observer2 = new Client("client2");
//        Observer observer3 = new Client("client3");
//
//        ss.subsCribe(observer3,"p1");
//        ss.subsCribe(observer1,"p2");
//        ss.subsCribe(observer2,"p2");
//
//        System.out.println();
//        ss.increasePrice("p2",200);
//        ss.unSubscribe(observer2,"p2");
//    }
//}
