package com.arafat.pattern.behavioural.ObserverPattern;


public class StockEntity {

    private String name;
    private double price;
    private int count;

    public StockEntity(String name, double price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }


    public  void increasePrice(double incr){
        this.price+=incr;
    }
    public  void decreasePrice(double decr){
        this.price-=decr;
    }



    public String getName() {
        return name;
    }

    public StockEntity setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public StockEntity setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getCount() {
        return count;
    }

    public StockEntity setCount(int count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        return "StockName:: "+this.name +"\n\tPrice: "+ this.price +"\n\tCount: "+ this.count+"\n";
    }
}

