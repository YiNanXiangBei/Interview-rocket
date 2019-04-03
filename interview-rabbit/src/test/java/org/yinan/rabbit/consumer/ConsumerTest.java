package org.yinan.rabbit.consumer;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.*;

/**
 * @author yinan
 * @date 19-3-31
 */
public class ConsumerTest {

    private Consumer consumer = new Consumer();

    private static final Lock LOCK = new ReentrantLock();

    private static final Condition STOP = LOCK.newCondition();

    @Test
    public void receiver() throws IOException {
        consumer.receiver();
        try {
            LOCK.lock();
            STOP.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }

    }
}