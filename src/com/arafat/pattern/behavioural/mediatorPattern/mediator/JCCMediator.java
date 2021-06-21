package com.arafat.pattern.behavioural.mediatorPattern.mediator;

import com.arafat.pattern.behavioural.mediatorPattern.collegue.ServiceProvider;

public interface JCCMediator  {
    void route_Service(String provider);

    void recieveRequest(ServiceProvider serviceProvider,String serviceName);

}
