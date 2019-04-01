package org.yinan.rabbit.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import org.yinan.rabbit.client.RabbitClient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.TreeSet;

/**
 * @author yinan
 * @date 19-3-28
 */
public class Producer {

    private static TreeSet<Long> tags = new TreeSet<>();

    private final static String EXCHANGE_NAME = "test4";

    private final static String ROUTING_KEY = "test.update";

    private volatile static Long id = 1L;


    private Channel channel = null;

    public Producer() throws IOException {
        RabbitClient client = new RabbitClient();
        channel = client.newChannel();
        channel.confirmSelect();
    }

    public void publish(String message) throws IOException, InterruptedException {
        byte[] messages = message.getBytes(Charset.forName("UTF-8"));
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(deliveryTag);
                tags.remove(deliveryTag);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {

            }
        });
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, messages);
        tags.add(id ++);
        channel.waitForConfirms();
    }

}
