package org.yinan.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yinan
 * @date 2021/3/16
 */
public class GCOverheadDemo {
    /**
     * JVM 参数配置： -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     */
    public static void main(String[] args) throws Throwable {
        int i = 0;
        List<String> list = new ArrayList<String>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("***********" + i);
            e.printStackTrace();
            throw e;
        }
    }
}
