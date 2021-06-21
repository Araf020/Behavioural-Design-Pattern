package com.arafat.pattern.behavioural.mediatorPattern.mediator;

import com.arafat.pattern.behavioural.mediatorPattern.collegue.ServiceProvider;

import java.util.LinkedList;
import java.util.Queue;

public class JCC implements JCCMediator {

    private  Queue<ServiceProvider> JWSARequestQueue;
    private  Queue<ServiceProvider> JPDCRequestQueue;
    private  Queue<ServiceProvider> JRTARequestQueue;
    private  Queue<ServiceProvider> JTRCRequestQueue;


    public JCC() {
        JWSARequestQueue = new LinkedList<ServiceProvider>();
        JPDCRequestQueue = new LinkedList<ServiceProvider>();
        JRTARequestQueue = new LinkedList<ServiceProvider>();
        JTRCRequestQueue = new LinkedList<ServiceProvider>();

    }

    @Override
    public void route_Service(String provider) {


        if (provider.equalsIgnoreCase("JWSA")){

            if (JWSARequestQueue.isEmpty()){
                System.out.println("No request found!");
                return;
            }
            String name = JWSARequestQueue.remove().getName();
            System.out.println("JWSA served Water to "+ name);

        }

        else if (provider.equalsIgnoreCase("JPDC")){

            if (JPDCRequestQueue.isEmpty()){
                System.out.println("No request found!");
                return;
            }
            String name = JPDCRequestQueue.remove().getName();
            System.out.println("JPDC served POWER to "+ name);
        }
        else if (provider.equalsIgnoreCase("JRTA")){

            if (JRTARequestQueue.isEmpty()){
                System.out.println("No request found!");
                return;
            }
            String name = JRTARequestQueue.remove().getName();
            System.out.println("JRTA served TRANSPORT to "+ name);
        }

        else if (provider.equalsIgnoreCase("JTRC")){

            if (JTRCRequestQueue.isEmpty()){
                System.out.println("No request found!");
                return;
            }
            String name = JTRCRequestQueue.remove().getName();
            System.out.println("JTRC served TELECOM to "+ name);
        }

    }


    @Override
    public void recieveRequest(ServiceProvider serviceProvider,String serviceName) {

        if (serviceName.equalsIgnoreCase("POWER")){
            System.out.println(serviceProvider.getName()+" requests for POWER service");
            JPDCRequestQueue.add(serviceProvider);
        }
        else if (serviceName.equalsIgnoreCase("WATER")){
            System.out.println(serviceProvider.getName()+" requests for WATER service");
            JWSARequestQueue.add(serviceProvider);
        }

        else if (serviceName.equalsIgnoreCase("TRANSPORT")){
            System.out.println(serviceProvider.getName()+" requests for TRANSPORT service");
            JRTARequestQueue.add(serviceProvider);
        }

        else if (serviceName.equalsIgnoreCase("TELECOM")){

            System.out.println(serviceProvider.getName()+" requests for TELECOM service");
            JTRCRequestQueue.add(serviceProvider);
        }

        else {
            System.out.println("Wrong Service Request!");
        }

    }
}
