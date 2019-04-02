package org.yinan.rabbit.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.yinan.rabbit.client.RabbitClient;
import org.yinan.rabbit.constant.Constant;
import org.yinan.rabbit.model.Amount;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author yinan
 * @date 19-3-28
 */
public class Consumer {

    List<String> tags = new LinkedList<>();

    private Channel channel = null;

    public Consumer() {
        try {
            RabbitClient client = new RabbitClient();
            channel = client.newChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  void receiver() throws IOException {
        boolean autoAck = false;
        //异步调用的方法，即只要mq有消息，就会回调到这里，所以只需要保证代码没有停止，就可以一直接收消息
        channel.basicConsume(Constant.DELAY_QUEUE, autoAck, "", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] bodys) throws IOException {
                try {
                    Amount amount = deserial(bodys);
                    String serialNo = amount.getSerialNo();
//                    TimeUnit.SECONDS.sleep(20);
                    if (tags.contains(serialNo)) {
                        //如果basicNack里面的第三个参数设置为true那么执行nack的消息将进入队列里面重新进行排队，
                        //这样如果该消息一直不会被消费，将会十分浪费系统性能
                        channel.basicNack(envelope.getDeliveryTag(), false, false);
                        return;
                    } else {
                        tags.add(amount.getSerialNo());
                    }
                    System.out.println("Consumer have received : " + amount.toString());
                } catch (ClassNotFoundException e) {
                    System.err.println(e.toString());
                }
                //如果这里注释掉，那么消息会变成unacked状态
                channel.basicAck(envelope.getDeliveryTag(), false);
            }

        });

    }


    private Amount deserial(byte[] messages) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(messages);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return  (Amount) objectInputStream.readObject();
    }
}
