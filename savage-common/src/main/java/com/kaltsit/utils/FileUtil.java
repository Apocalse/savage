package com.kaltsit.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtil {


    public static void main(String[] args) throws IOException {
        // URL of the image to download
        String imageUrl = "http://www.cs090.com/uploads/userup/a/701493/2023/1E2256435-3955O2_2000x2000.jpg";

        // Create URL object and open input stream to the image
        URL url = new URL(imageUrl);
        InputStream inputStream = url.openStream();


        // Output stream to save the image to file
        FileOutputStream outputStream = new FileOutputStream("image.jpg");

        // Read bytes from the input stream and write to the output stream
        byte[] buffer = new byte[2048];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        // Close streams
        inputStream.close();
        outputStream.close();

        System.out.println("Image downloaded successfully.");
    }


    /**
     * 得到文件流
     * @param url  网络图片URL地址
     * @return
     */
    public static byte[] getFileStream(String url){
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

}
