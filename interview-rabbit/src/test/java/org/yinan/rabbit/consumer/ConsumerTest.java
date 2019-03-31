package org.yinan.rabbit.consumer;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author yinan
 * @date 19-3-31
 */
public class ConsumerTest {

    Consumer consumer = new Consumer();

    @Test
    public void receiver() throws IOException {
        consumer.receiver();
    }
}