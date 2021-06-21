package com.arafat.pattern.behavioural.ObserverPattern.client;


import com.arafat.pattern.behavioural.ObserverPattern.subject.Server;
import com.arafat.pattern.behavioural.ObserverPattern.StockEntity;

public abstract class Observer {

    Server server;
    public abstract void update(StockEntity stk, String msg);
    public abstract int getID();
}