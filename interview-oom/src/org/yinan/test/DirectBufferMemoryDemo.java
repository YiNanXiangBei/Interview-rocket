package org.yinan.test;

import java.nio.ByteBuffer;

/**
 * @author yinan
 * @date 2021/3/16
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        // 如果什么都不配置 JVM 内存，大概是本地内存的 1/4
        System.out.println("配置的 maxDirectMemory" + (sun.misc.VM.maxDirectMemory()/ (double)1024 / 1024) + "MB");
        // 这里调用的是 jdk 包中 rt.jar 包中的方法  sun.misc.VM.maxDirectMemory()
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // -Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m  （把系统堆内存设置成 5MB）
        // 配置 5MB 实际使用 6MB
        ByteBuffer buffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
