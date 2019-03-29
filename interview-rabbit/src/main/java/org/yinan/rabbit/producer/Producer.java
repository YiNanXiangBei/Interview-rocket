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

    private final static String EXCHANGE_NAME = "";

    private final static String ROUTING_KEY = "test";

    private final static String QUEUE_NAME = "";

    private final static String TYPE = "";

    private Channel channel = null;

    private RabbitClient client = new RabbitClient();

    public Producer() throws IOException {
        channel = client.newChannel();
    }

    private void publish(String message) throws IOException {
        byte[] messages = message.getBytes(Charset.forName("UTF-8"));
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, messages);
    }

}
