package com.xiaomi;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhen
 * Date: 14-8-27
 * Time: 下午2:33
 * To change this template use File | Settings | File Templates.
 */

public abstract class EndPoint{

    protected int partition = 1024;
    protected Channel channel;
    protected Connection connection;
    protected int routingKey;

    public EndPoint(int routingKey) throws IOException{
        this.routingKey = routingKey;

        //Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();

        Address[] addrArr = new Address[]{ new Address("10.237.12.1"), new Address("10.237.12.2")};
        //Connection conn = factory.newConnection(addrArr);

        //getting a connection
        connection = factory.newConnection(addrArr);

        //creating a channel
        channel = connection.createChannel();

        //declaring a queue for this channel. If queue does not exist,
        //it will be created on the server.
            channel.queueDeclare("--"+routingKey%partition+"", false, false, true, null);

        channel.basicQos(10);
    }


    /**
     * 关闭channel和connection。并非必须，因为隐含是自动调用的。
     * @throws IOException
     */
    public void close() throws IOException {
        this.channel.close();
        this.connection.close();
    }
}