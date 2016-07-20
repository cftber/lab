package com.tgzhao.core.io;

import java.io.*;

/**
 * Created by tgzhao on 16/7/17.
 */
public abstract class IOUtil {
    public static void copy(InputStream inputStream, String file) throws IOException {
        copy(inputStream, new File(file));
    }

    public static void copy(InputStream inputStream, File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(readBytes(inputStream));
        outputStream.flush();
        outputStream.close();
    }

    private static byte[] readBytes(InputStream inputStream) throws IOException {
        byte[] stringBytes = new byte[0];
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) > 0) {
            byte[] tempStringBytes = new byte[stringBytes.length + len];
            System.arraycopy(stringBytes, 0, tempStringBytes, 0, stringBytes.length);
            System.arraycopy(bytes, 0, tempStringBytes, stringBytes.length, len);
            stringBytes = tempStringBytes;
        }
        return stringBytes;
    }

}
