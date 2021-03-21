package org.yinan.test;

/**
 * @author yinan
 * @date 2021/3/16
 */
public class JavaVMStackSOF {
    private int length = 0;

    private void recursion() {
        length++;
        recursion();
    }

    public static void main(String[] args) {
        JavaVMStackSOF sof = new JavaVMStackSOF();

        try {
            sof.recursion();
        } catch (Throwable e) {
            System.out.println("length = " + sof.length);
            e.printStackTrace();
        }
    }
}
