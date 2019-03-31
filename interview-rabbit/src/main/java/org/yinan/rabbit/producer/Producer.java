package org.yinan.rabbit.producer;

import com.rabbitmq.client.Channel;
import org.yinan.rabbit.client.RabbitClient;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author yinan
 * @date 19-3-28
 */
public class Producer {

    private final static String EXCHANGE_NAME = "test4";

    private final static String ROUTING_KEY = "test.update";


    private Channel channel = null;

    public Producer() throws IOException {
        RabbitClient client = new RabbitClient();
        channel = client.newChannel();
    }

    public void publish(String message) throws IOException {
        byte[] messages = message.getBytes(Charset.forName("UTF-8"));
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, messages);
    }

}
