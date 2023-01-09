package com.ayan.ActiveMQ.resource;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.*;

@RestController
@RequestMapping("/producer")
public class Producer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ActiveMQConnectionFactory factory;

    @Autowired
    Queue queue;

//    To send the message to the default queue
    @PostMapping("/queue")
    public Boolean SendToQueue(@RequestBody String queueMessage){
        try{
            jmsTemplate.convertAndSend(queue, queueMessage);
            return true;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }


//  To send the message to other queue

    @PostMapping("/sendToMyqueue")
    public Boolean sendToOtherQueue(@RequestParam String msg){
        try{
//          Send the message to a different queue if iq is not there by default
//          our application will create the queue and send message to that
            Connection connection= factory.createConnection();
            Session session= connection.createSession();
            Destination des= session.createQueue("test_queue1");

//          Sending message to our created queue instance
            jmsTemplate.convertAndSend(des, msg);
            return true;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return false;
    }








}
