package com.kaltsit.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

public class ThumbnailUtil {

    private static int count = 1;

    /**
     * 根据指定大小和指定经度压缩图片
     *
     * @param srcPath     源图片地址
     * @param desPath     目标图片地址
     * @param desFileSize 指定图片大小，单位kb
     * @param accuracy    精度，递归压缩的比率，建议小于0.9
     */
    public static void compressPictureForScale(String srcPath, String desPath, long desFileSize, double accuracy) {
        if (StringUtils.isEmpty(srcPath) || StringUtils.isEmpty(desPath)) {
            return;
        }
        if (!new File(srcPath).exists()) {
            return;
        }
        try {
            File srcFile = new File(srcPath);
            long srcFileSize = srcFile.length();
            System.out.println("源图片: " + srcPath + ", 大小: " + srcFileSize / 1024 + "kb");
            //1.裁剪图片
            BufferedImage bufferedImage = ImageIO.read(Files.newInputStream(srcFile.toPath()));
            // 宽度
            int width = bufferedImage.getWidth();
            // 高度
            int height = bufferedImage.getHeight();
            if (width >= height) {
                width = (height / 4) * 3;
            } else {
                height = ((width / 3)) * 4;
            }
            bufferedImage = Thumbnails.of(bufferedImage).sourceRegion(Positions.CENTER, width, height).scale(1f).outputQuality(1).asBufferedImage();
            //2.递归压缩，直到目标文件大小小于desFileSize
            compressPicCycle(bufferedImage, desFileSize, accuracy);

            File desFile = new File(desPath);
            System.out.println("目标图片: " + desPath + ", 大小: " + desFile.length() / 1024 + "kb");
            System.out.println("图片压缩完成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void compressPicCycle(BufferedImage bufferedImage, long desFileSize, double accuracy) throws IOException {

        //创建一个ByteArrayOutputStream
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //把BufferedImage写入ByteArrayOutputStream
        ImageIO.write(bufferedImage, "jpg", os);
        //ByteArrayOutputStream转成InputStream
        InputStream input = new ByteArrayInputStream(os.toByteArray());
        //InputStream转成MultipartFile
        MultipartFile multipartFile = new MockMultipartFile("file", "file.jpg", "text/plain", input);

        long srcFileSizeJPG = multipartFile.getSize();
        System.out.println(srcFileSizeJPG);
        //2.判断大小，如果小于desFileSize，不用压缩，如果大于等于50kb,需要压缩
        if (srcFileSizeJPG <= desFileSize * 1024) {
            System.out.println(count + "次");
            Thumbnails.of(bufferedImage).scale(1).outputQuality(1).toFile("C:\\Users\\20201236\\Pictures\\Saved Pictures\\11.jpg");
        }
        System.out.println(count + "次");
        bufferedImage = Thumbnails.of(bufferedImage).size(240, 320).outputQuality(accuracy).asBufferedImage();
        count++;
        compressPicCycle(bufferedImage, desFileSize, accuracy);
    }

    public static void main(String[] args) {
        String srcPath = "C:\\Users\\20201236\\Pictures\\Saved Pictures\\11gq.jpg";
        String desPath = "C:\\Users\\20201236\\Pictures\\Saved Pictures\\11.jpg";
        ThumbnailUtil.compressPictureForScale(srcPath, desPath, 25, 0.9);
    }
}


