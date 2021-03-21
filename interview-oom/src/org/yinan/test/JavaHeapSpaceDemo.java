package org.yinan.test;

/**
 * @author yinan
 * @date 2021/3/16
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        // 我配置了虚拟机参数 -Xms10m -Xmx10m    初始化堆内存和最大堆内存都是 10m
        byte[] b = new byte[20 * 1024 * 1024];   // 这里 new 了 20m 的字节数组
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    }
}
