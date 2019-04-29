package org.yinan.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yinan
 * @date created in 下午3:42 19-4-29
 */
public class HotSwapURLCLassLoader extends URLClassLoader {

    //缓存加载class文件的最后修改时间
    public static Map<String, Long> cacheLastModifyTimeMap = new HashMap<>();

    //class类所在路径
    public static String projectClassPath = "/home/laowang/gitwarehouse/Interview-rocket/interview-classloader/target/classes/org/yinan/loader/";

    //类所在包
    public static String packagePath = "org/yinan/loader/";

    private static HotSwapURLCLassLoader hcl = new HotSwapURLCLassLoader();

    public HotSwapURLCLassLoader() {
        super(getMyURLs());
    }

    public static HotSwapURLCLassLoader getClassLoader() {
        return hcl;
    }

    private static URL[] getMyURLs() {
        URL url = null;
        try {
            url = new File(projectClassPath).toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new URL[] {url};
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class clazz = null;
        //查看HotSwapURLClassLoader 实例缓存下是否已经加载过class
        //不同的HotSwapURLClassLoader实例是不是共享缓存
        clazz = findLoadedClass(name);
        if (clazz != null) {
            if (resolve) {
                resolveClass(clazz);
            }
            //如果class类被修改过，则重新加载
            if (idModify(name)) {
                hcl = new HotSwapURLCLassLoader();
                clazz = customLoad(name, hcl);
            }
            return (clazz);
        }

        if (name.startsWith("java.")) {
            try {
                ClassLoader system = ClassLoader.getSystemClassLoader();
                clazz = system.loadClass(name);
                if (clazz != null) {
                    if (resolve) {
                        resolveClass(clazz);
                    }
                    return (clazz);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return customLoad(name, this);
    }


    public Class load(String name) throws Exception {
        return loadClass(name);
    }

    /**
     * 自定义加载
     * @param name
     * @param cl
     * @return
     * @throws ClassNotFoundException
     */
    public Class customLoad(String name, ClassLoader cl) throws ClassNotFoundException {
        return customLoad(name, false, cl);
    }

    /**
     * 自定义加载
     * @param name
     * @param resolve
     * @param cl
     * @return
     * @throws ClassNotFoundException
     */
    public Class customLoad(String name, boolean resolve, ClassLoader cl) throws ClassNotFoundException {
        //调用的是URLClassLoader里面重载了ClassLoader的findClass()方法
        Class clazz = ((HotSwapURLCLassLoader)cl).findClass(name);
        if (resolve) {
            ((HotSwapURLCLassLoader)cl).resolveClass(clazz);
        }
        //缓存加载class文件的最后修改时间
        long lastModifyTime = getClassLastModifyTime(name);
        cacheLastModifyTimeMap.put(name, lastModifyTime);
        return clazz;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    /**
     * .class文件最近修改的时间
     * @param name
     * @return
     */
    private long getClassLastModifyTime(String name) {
        String path = getClassCompletePath(name);
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException(new FileNotFoundException(name));
        }
        return file.lastModified();
    }

    /**
     * 判断这个文件和上次比是否修改过
     * @param name
     * @return
     */
    private boolean idModify(String name) {
        long lastModify = getClassLastModifyTime(name);
        long previousModifyTime = cacheLastModifyTimeMap.get(name);
        return lastModify > previousModifyTime;
    }

    /**
     * .class文件的完整路径
     * @param name
     * @return
     */
    private String getClassCompletePath(String name) {
        String simpleName = name.substring(name.lastIndexOf(".") + 1);
        return projectClassPath + packagePath + simpleName + ".class";
    }
}
