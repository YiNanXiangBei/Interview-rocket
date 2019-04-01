package org.yinan.rabbit.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.yinan.rabbit.client.RabbitClient;
import org.yinan.rabbit.constant.Constant;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author yinan
 * @date 19-3-28
 */
public class Consumer {




    private Channel channel = null;

    public Consumer() {
        try {
            RabbitClient client = new RabbitClient();
            channel = client.newChannel();
            channel.queueDeclare(Constant.QUEUE_NAME, true, false, false, null);
            channel.queueBind(Constant.QUEUE_NAME, Constant.EXCHANGE_NAME, Constant.CONSUMER_ROUTING_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  void receiver() throws IOException {
        boolean autoAck = false;
        //异步调用的方法，即只要mq有消息，就会回调到这里，所以只需要保证代码没有停止，就可以一直接收消息
        channel.basicConsume(Constant.QUEUE_NAME, autoAck, "", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] bodys) throws IOException {
                String message = new String(bodys, StandardCharsets.UTF_8);
                System.out.println("Consumer have received : " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }

        });

    }

}
