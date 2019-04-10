package org.yinan.loader;

import org.junit.Test;
import org.yinan.entity.People;

import static org.junit.Assert.*;

/**
 * @author yinan
 * @date 19-4-10
 */
public class MyClassLoaderTest {

    @Test
    public void findClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader();
//        People people = new People();
        Class<?> clazz = Class.forName("org.yinan.entity.People", true, classLoader);
        Object o = clazz.newInstance();
        System.out.println(o);
        System.out.println(o.getClass().getClassLoader());
    }
}