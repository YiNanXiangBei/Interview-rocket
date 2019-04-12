package org.yinan;

import com.google.common.collect.Sets;
import org.yinan.loader.MyClassLoader;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author yinan
 * @date created in 下午2:51 19-4-12
 */
public class Application {

    private void printVersion() {
        System.out.println("版本号1");
    }



    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String swapPath = "/home/laowang/gitwarehouse/Interview-rocket/interview-classloader/target/classes/";
                String className = "org.yinan.Application";
                MyClassLoader myClassLoader = new MyClassLoader(Thread.currentThread().getContextClassLoader(), className, swapPath);
                try {
                    Application application = (Application) myClassLoader.loadClass().newInstance();
                    application.printVersion();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2000);
    }

}
