package com.ayan.ActiveMQ.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "ayan_queue")
    public void consumeDefaultQueue(String s){
        System.out.println(s.toString());
    }

}
