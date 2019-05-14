package com.everis.cars.boundary;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;

@Stateless
@LocalBean
public class MessageProducer {

        @Inject
        JMSContext ctx;
        
        @Resource(lookup = "jms/CarsApp_API_ManagementQueue")
        Queue queue;
        
        public void sendMessage(String msg){
            ctx.createProducer().send(queue, msg);
        }

}

