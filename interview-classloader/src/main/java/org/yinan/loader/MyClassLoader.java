package org.yinan.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author yinan
 * @date 19-4-10
 */
public class MyClassLoader extends ClassLoader {

    public MyClassLoader() {
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        File file = new File("/home/laowang/gitwarehouse/Interview-rocket/interview-classloader/src/main/java/org/yinan/entity/People.class");
        try {
            byte[] bytes = getClassBytes(file);
            return this.defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }


    private byte[] getClassBytes(File file) throws Exception {
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        WritableByteChannel writableByteChannel = Channels.newChannel(outputStream);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            int i = channel.read(buffer);
            if (i == 0 || i == -1) {
                break;
            }
            buffer.flip();
            writableByteChannel.write(buffer);
            buffer.clear();
        }
        inputStream.close();
        return outputStream.toByteArray();
    }
}
