package com.arafat.pattern.behavioural.mediatorPattern.collegue;

import com.arafat.pattern.behavioural.mediatorPattern.mediator.JCC;
import com.arafat.pattern.behavioural.mediatorPattern.mediator.JCCMediator;

public abstract class ServiceProvider {
    protected JCCMediator mediator;
    protected String name;

    public ServiceProvider(JCCMediator med, String name){
        this.mediator=med;
        this.name=name;
    }

    public abstract void request(String Requestservice);

    public abstract void serve();
    public abstract String getName();

}

