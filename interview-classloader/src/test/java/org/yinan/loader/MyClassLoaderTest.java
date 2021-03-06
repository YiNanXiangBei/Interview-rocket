package org.yinan.loader;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.yinan.entity.People;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.*;

/**
 * @author yinan
 * @date 19-4-10
 */
public class MyClassLoaderTest {

    private void printVersion() {
        System.out.println("版本号1");
    }



    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String swapPath = MyClassLoader.class.getResource("").getPath();
                String className = "org.yinan.loader.MyClassLoaderTest";
                MyClassLoader myClassLoader = new MyClassLoader(Thread.currentThread().getContextClassLoader(), className, swapPath);
                try {
                    Object o = myClassLoader.loadClass(className).newInstance();
                    o.getClass().getMethod("printVersion").invoke(o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2000);
    }
}