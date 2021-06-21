package com.arafat.pattern.behavioural.mediatorPattern;

import com.arafat.pattern.behavioural.mediatorPattern.collegue.ServiceProvider;
import com.arafat.pattern.behavioural.mediatorPattern.collegue.ServiceProviderIImpl;
import com.arafat.pattern.behavioural.mediatorPattern.mediator.JCC;
import com.arafat.pattern.behavioural.mediatorPattern.mediator.JCCMediator;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Driver {

    public static void main(String[] args) throws NullPointerException {

        JCCMediator mediator=null;
        ServiceProvider JWSA =null;
        ServiceProvider JPDC = null;
        ServiceProvider JRTA = null;
        ServiceProvider JTRC = null;
        while (true){
            System.out.println("\n*Sample Command\n\t>>Init\n" +
                    "\t>>JWSA POWER \n" +
                    "\t>>JRTA POWER \n" +
                    "\t>>JPDC TELECOM\n" +
                    "\t>>JPDC SERVE\n" +
                    "\t>>JTRC SERVE\nEnter a command: ");

            String command = new Scanner(System.in).nextLine();
            StringTokenizer stringTokenizer = new StringTokenizer(command);

            String provider=null, servicetype=null;
            if (command.equalsIgnoreCase("init")){
                mediator= new JCC();
                JWSA= new ServiceProviderIImpl(mediator,"JWSA");
                JPDC= new ServiceProviderIImpl(mediator,"JPDC");
                JRTA= new ServiceProviderIImpl(mediator,"JRTA");
                JTRC= new ServiceProviderIImpl(mediator,"JTRC");
                System.out.println("\n\t######All four services are initiated through mediator#######\n");

            }

            else if(stringTokenizer.countTokens()>=2) {
                provider = stringTokenizer.nextToken();
                servicetype = stringTokenizer.nextToken();


                if (mediator==null){
                    System.out.println("**Components are not Initialized. Please type \"init\" to continue\n ");
                    continue;
                }

                if (provider.equalsIgnoreCase("JWSA")) {
                    if (servicetype.equalsIgnoreCase("SERVE"))
                        JWSA.serve();
                    else
                        JWSA.request(servicetype);


                }

                else if (provider.equalsIgnoreCase("JPDC")) {
                    if (servicetype.equalsIgnoreCase("SERVE"))
                        JPDC.serve();
                    else
                        JPDC.request(servicetype);


                }

                else if (provider.equalsIgnoreCase("JRTA")) {
                    if (servicetype.equalsIgnoreCase("SERVE"))
                        JRTA.serve();
                    else
                        JRTA.request(servicetype);


                }
                else if (provider.equalsIgnoreCase("JTRC")) {
                    if (servicetype.equalsIgnoreCase("SERVE"))
                        JTRC.serve();
                    else
                        JTRC.request(servicetype);


                }
                else {
                    System.out.println("Wrong input. Try again!");
                }
            }

        }
    }
}
