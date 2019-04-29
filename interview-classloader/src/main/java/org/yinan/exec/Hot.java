package org.yinan.exec;

/**
 * @author yinan
 * @date created in 下午4:27 19-4-29
 */
public class Hot {
    public void hot() {
        System.out.println("version 1: " + this.getClass().getClassLoader());
    }
}
