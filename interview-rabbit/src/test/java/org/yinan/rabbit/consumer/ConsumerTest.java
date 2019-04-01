package org.yinan.rabbit.consumer;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
        for (;;) {

        }

    }
}