package org.yinan.rabbit.client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.yinan.rabbit.config.Config;
import org.yinan.rabbit.config.InitConfig;
import org.yinan.rabbit.constant.Constant;

import java.io.IOException;
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
        channel.exchangeDeclare(Constant.EXCHANGE_NAME, Constant.TYPE, true);
        return channel;
    }


    public void close(Channel oldChannel) throws IOException, TimeoutException {
        if (oldChannel != null) {
            oldChannel.close();
        }
    }
}
