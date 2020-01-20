package com.xhjc.io;

import org.junit.Test;

import java.io.*;

/**
 * @Author xhj
 * @Description io流相关知识总结
 * @Date 2020/1/20 11:25
 **/
public class FileHandleDemo {

    //文件读取，超出缓存文件出现换行
    // 建议方案，逐行读取
    @Test
    public void readStream() {
        //字节流读文件
        InputStream in = null;
        OutputStream os = null;
        try {
            in = FileHandleDemo.class.getClassLoader().getResourceAsStream("io/test001.xml");

            //写文件
            os = new FileOutputStream(new File("testCopy.xml"));

            byte[] buffer = new byte[4024];
            while (in.read(buffer) != -1) {
                System.out.println(new String(buffer, "utf-8"));
                //写文件
                os.write(buffer);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally{
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //缓冲流
    //文件读取，超出缓存文件出现换行
    @Test
    public void readStream2() throws IOException {
        //字节流读文件
        InputStream in = null;
        OutputStream os = null;
        BufferedInputStream bis = null;
        try {
            in = FileHandleDemo.class.getClassLoader().getResourceAsStream("io/test001.xml");
            bis = new BufferedInputStream(in);
            byte[] data = new byte[1024];
            //写文件
            os = new FileOutputStream(new File("testCopy.xml"));
            int len;
            while ((len = bis.read(data)) != -1) {
                System.out.println(new String(data, 0, len));
                os.write(data);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            if(bis != null){
                bis.close();
            }
            if(in != null){
                in.close();
            }
            if(os != null){
                os.close();
            }

        }

    }

    //文件读取，每行读取并写入
    @Test
    public void readStream3() {
        //字节流读文件
        BufferedReader reader = null;
        BufferedWriter bw =null;

        try {
            reader = new BufferedReader(new FileReader(new File("D:\\workspaces\\git_resources\\KnowledgePoints\\workspases\\practise\\BaseDemoMaven\\src\\test\\resources\\io\\test001.xml")));
            //读取将会出现乱码情况
            //reader = new BufferedReader(new FileReader(new File("D:\\施工文件文件.doc")));
            bw = new BufferedWriter(new FileWriter(new File("D:\\from.txt")));
            String lineStr;
            while ((lineStr = reader.readLine()) != null) {
                System.out.println(lineStr);
                bw.newLine();
                bw.write(lineStr);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }finally{
            if (bw != null) {
                try {
                    //刷新缓存区,必须
                    bw.flush();
                    bw.close();
                } catch (IOException ignored) {

                }
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //word文件复制
    @Test
    public void copyPdfOrWord() throws IOException {
        FileInputStream is = null;
        FileOutputStream os = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
            is = new FileInputStream(new File("D:\\施工文件文件.doc") );
            os = new FileOutputStream(new File("D:\\施工文件文件copy.doc"));
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(os);
            int temp;
            while ((temp = bis.read()) != -1) {
                bos.write(temp);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if (bis != null) {
                bis.close();
                is.close();
            }
            if (bos != null) {
                bos.close();
                os.close();
            }
        }
    }

}
