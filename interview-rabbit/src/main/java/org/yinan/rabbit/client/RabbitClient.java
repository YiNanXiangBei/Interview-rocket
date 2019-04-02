package org.yinan.rabbit.client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.yinan.rabbit.config.Config;
import org.yinan.rabbit.config.InitConfig;
import org.yinan.rabbit.constant.Constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author yinan
 * @date 19-3-28
 */
public class RabbitClient {

    private volatile static Connection connection = null;

    private volatile static ConnectionFactory factory = new ConnectionFactory();
    
    static {
        Config config = new InitConfig().init("/home/laowang/gitwarehouse/Interview-rocket/interview-rabbit/src/main/resources/application.properties");
        factory.setUsername(config.getUsername());
        factory.setPassword(config.getPassword());
        factory.setVirtualHost(config.getVirtualHost());
        factory.setHost(config.getHost());
        factory.setPort(config.getPort());
        try {
            connection = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            System.err.println("[RabbitClient] " + e.getMessage());
        }
    }

    public Channel newChannel() throws IOException {
        Channel channel = connection.createChannel();
        Map<String, Object> arguments = new HashMap<>();
        channel.exchangeDeclare(Constant.DELAY_EXCHANGE, Constant.TYPE, true);

        arguments.put("x-dead-letter-exchange", Constant.DELAY_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", Constant.CONSUMER_ROUTING_KEY);
        //声明一个延时队列
        channel.queueDeclare(Constant.DELAY_QUEUE, true, false, false, arguments);
        //声明一个正常队列
        channel.queueDeclare(Constant.QUEUE_NAME, true, false, false, null);
        //延时队列和交换器进行绑定
        channel.queueBind(Constant.QUEUE_NAME, Constant.DELAY_EXCHANGE, Constant.CONSUMER_ROUTING_KEY);
        return channel;
    }


    public void close(Channel oldChannel) throws IOException, TimeoutException {
        if (oldChannel != null) {
            oldChannel.close();
        }
    }
}
