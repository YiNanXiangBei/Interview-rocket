package org.yinan;

import org.yinan.loader.HotSwapURLCLassLoader;

import java.lang.reflect.Method;

/**
 * @author yinan
 * @date created in 下午4:28 19-4-29
 */
public class TestHotSwap {
    public static void main(String[] args) throws Exception{
        Thread t = new Thread(new MointorHotSwap());
        t.start();
    }
}

class MointorHotSwap implements Runnable {

    private String className = ".home.laowang.gitwarehouse.Interview-rocket.interview-classloader.target.classes.org.yinan.exec.Hot";
    private Class hotClazz = null;
    private HotSwapURLCLassLoader hotSwapURLCLassLoader = null;

    @Override
    public void run() {
        try {
            while (true) {
                init();
                Object hot = hotClazz.newInstance();
                Method method = hotClazz.getMethod("hot");
                method.invoke(hot, null);
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception{
        hotSwapURLCLassLoader = HotSwapURLCLassLoader.getClassLoader();
        hotClazz = hotSwapURLCLassLoader.load(className);
    }
}
