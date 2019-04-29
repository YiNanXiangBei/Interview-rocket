package org.yinan.exec;

/**
 * @author yinan
 * @date 19-4-15
 */
public class VersionControl {
    public void printVersion() {
        System.out.println(getClass().getClassLoader().getClass());
        System.out.println("版本号2221");
    }
}
