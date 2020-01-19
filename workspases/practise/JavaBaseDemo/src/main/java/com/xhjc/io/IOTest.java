package com.xhjc.io;


import java.io.*;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020/1/19 16:04
 **/
public class IOTest {
    public static void main(String[] args) {

        writeFile(readFile());
    }

    //读文件,该方式会被截断
    public static String readFile() {
        //文件流读取
        InputStream in = IOTest.class.getClassLoader().getResourceAsStream("io/test001.xml");
        byte[] buf = new byte[4048];
        String str = "";
        int len = 0;
        try {
            while ((len = in.read(buf)) != -1) {
                str = new String(buf, 0, len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    //写文件
    public static String writeFile(String fileStr) {
        OutputStream os = null;
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(fileStr.getBytes());
            os = new FileOutputStream("D://test.txt");
            byte[] bytes = new byte[4048];
            int len = 0;
            while ((len = in.read(bytes, 0, bytes.length)) != -1) {
                os.write(bytes, 0, len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }



}
