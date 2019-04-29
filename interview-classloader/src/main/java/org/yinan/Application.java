package org.yinan;

import com.google.common.collect.Sets;
import org.yinan.exec.VersionControl;
import org.yinan.loader.FileSystemClassLoader;
import org.yinan.loader.MyClassLoader;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author yinan
 * @date created in 下午2:51 19-4-12
 */
public class Application {

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String swapPath = "/home/tomcat/log";
                String className = "org.yinan.exec.VersionControl";

                FileSystemClassLoader classLoader = new FileSystemClassLoader(ClassLoader.getSystemClassLoader(), swapPath);


                try {
                    Class clazz = classLoader.loadClass(className);
//                    Class clazz = Class.forName(className, true, classLoader);
                    Object object = clazz.newInstance();
                    Method method = clazz.getDeclaredMethod("printVersion", null);
                    method.invoke(object, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2000);
    }

}
