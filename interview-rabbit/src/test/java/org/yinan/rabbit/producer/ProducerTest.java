package org.yinan.rabbit.producer;

import org.junit.Test;

import java.io.IOException;

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
        for (int i = 0; i < 100; i++) {
            producer.publish("test " + i);
        }

//        producer.confirms();

    }
}