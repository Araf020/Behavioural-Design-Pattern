package com.arafat.pattern.behavioural.ServerTest;


import com.arafat.pattern.behavioural.ServerTest.Server;
import com.arafat.pattern.behavioural.ServerTest.StockEntity;

public abstract class Observer {

    Server server;
    public abstract void update(StockEntity stk, String msg);
    public abstract int getID();
}