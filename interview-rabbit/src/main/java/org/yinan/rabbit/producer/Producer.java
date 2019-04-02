package org.yinan.rabbit.producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import org.yinan.rabbit.client.RabbitClient;
import org.yinan.rabbit.constant.Constant;
import org.yinan.rabbit.model.Amount;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.TreeSet;

/**
 * @author yinan
 * @date 19-3-28
 */
public class Producer {

    private static TreeSet<Long> tags = new TreeSet<>();



    private volatile static Long id = 1L;


    private Channel channel = null;

    public Producer() throws IOException {
        RabbitClient client = new RabbitClient();
        channel = client.newChannel();
        channel.confirmSelect();
    }

    public void publish(byte[] messages) throws IOException, InterruptedException {
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(deliveryTag);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(multiple);
            }
        });
        //发送编码方式为utf-8，设置持久化 deliveryMode:2 持久化  deliveryMode:1 非持久化
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2)
                .contentEncoding("UTF-8")
                .build();
        channel.basicPublish(Constant.EXCHANGE_NAME, Constant.PRODUCER_ROUTING_KEY, properties, messages);
        confirms();

    }

    public void confirms() throws InterruptedException {
        channel.waitForConfirms();
    }

    public byte[] serial(Serializable obj) throws IOException {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(obj);
        return outputStream.toByteArray();
    }

}
