package com.arafat.pattern.behavioural.mediatorPattern.collegue;

import com.arafat.pattern.behavioural.mediatorPattern.mediator.JCCMediator;

public class ServiceProviderIImpl extends ServiceProvider {


    public ServiceProviderIImpl(JCCMediator med, String name) {
        super(med, name);
    }

    @Override
    public void request(String serviceName) {

        mediator.recieveRequest(this,serviceName);

    }

    @Override
    public void serve() {

        mediator.route_Service(name);

    }
    public String getName(){
        return name;
    }

}
