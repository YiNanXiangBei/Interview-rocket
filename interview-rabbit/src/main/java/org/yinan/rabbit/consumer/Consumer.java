package org.yinan.rabbit.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.yinan.rabbit.client.RabbitClient;

import java.io.IOException;

/**
 * @author yinan
 * @date 19-3-28
 */
public class Consumer {

    private final static String EXCHANGE_NAME = "";

    private final static String ROUTING_KEY = "test";

    private final static String QUEUE_NAME = "";

    private final static String TYPE = "";

    private Channel channel = null;

    private RabbitClient client = new RabbitClient();

    public Consumer() {
        try {
            channel = client.newChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, TYPE, true);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  void receiver() throws IOException {
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, "", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] bodys) {

            }

        });
    }

}
