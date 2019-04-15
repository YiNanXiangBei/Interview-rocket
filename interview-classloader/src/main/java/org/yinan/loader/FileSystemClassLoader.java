package org.yinan.loader;

import java.io.*;

/**
 * @author yinan
 * @date 19-4-15
 */
public class FileSystemClassLoader extends ClassLoader{
    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClasszData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClasszData(String className) {
        String path = classNameToPath(className);
        try {
            InputStream inputStream = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int byteNumRead = 0;
            while ((byteNumRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, byteNumRead);
            }

            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private String classNameToPath(String className) {
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }
}
