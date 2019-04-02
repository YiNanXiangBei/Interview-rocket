package org.yinan.rabbit.producer;

import org.junit.Test;
import org.yinan.rabbit.model.Amount;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author yinan
 * @date 19-3-31
 */
public class ProducerTest {

    Producer producer = new Producer();

    public ProducerTest() throws IOException {
    }


    @Test
    public void publish() throws IOException, InterruptedException {
        for (int i = 0; i < 250; i++) {
            Amount amount = new Amount();
            amount.setSerialNo(i + "");
            amount.setMoney(String.valueOf(Math.random() * 1000));
            producer.publish(producer.serial(amount));
        }

//        producer.confirms();

    }
}