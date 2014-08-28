package com.xiaomi;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhen
 * Date: 14-8-27
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
public class Producer extends EndPoint {
    public Producer(int routingKey) throws IOException {
        super(routingKey);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("","--"+routingKey%partition+"", null, SerializationUtils.serialize(object));
    }

}
