package org.yinan.test;

/**
 * @author yinan
 * @date 2021/3/16
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 1; ;i++) {  // 无限 for 循环
            System.out.println("-------i =" + i);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "" + i).start();
        }
    }
}
