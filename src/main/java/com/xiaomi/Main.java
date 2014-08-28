package com.xiaomi;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhen
 * Date: 14-8-27
 * Time: 下午2:39
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public Main() throws Exception{

        int count = 1024;
        for (int i = 0 ; i<count; i++){
            QueueConsumer consumer = new QueueConsumer(i);
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
        }

        for (int j = 0 ; j < count; j++) {
            Producer producer = new Producer(j);
            for (int i = 0; i < 1; i++) {
                HashMap message = new HashMap();
                message.put("message number", i);
                producer.sendMessage(message);
                System.out.println("routingkey is "+j+" Message Number "+ i +" sent.");
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        new Main();
    }
}


