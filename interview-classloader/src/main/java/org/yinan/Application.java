package org.yinan;

import com.google.common.collect.Sets;
import org.yinan.exec.VersionControl;
import org.yinan.loader.FileSystemClassLoader;
import org.yinan.loader.MyClassLoader;

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
                String swapPath = "/home/laowang/gitwarehouse/Interview-rocket/interview-classloader/target/classes/";
                String className = "org.yinan.exec.VersionControl";

                FileSystemClassLoader classLoader = new FileSystemClassLoader(swapPath);


                try {
                    VersionControl versionControl = (VersionControl) classLoader.loadClass(className).newInstance();
                    versionControl.printVersion();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2000);
    }

}
