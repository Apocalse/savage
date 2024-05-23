package com.kaltsit.utils;

import com.kaltsit.exception.SavageException;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

public class ThumbnailUtil {

    public static String resizeTo10k_30k(String base64Img) {
        try {
            BufferedImage src = base64ToBufferedImage(base64Img);
            //指定图片长宽
            BufferedImage output = Thumbnails.of(src).size(240, 320).asBufferedImage();

            String base64 = decodeBase64String(output);
            while (base64.length() - base64.length() / 8 * 2 > 28 * 1024) { //大于28k，压缩
                output = Thumbnails.of(output).scale(0.9f).asBufferedImage();
                base64 = decodeBase64String(output);
            }
            while (base64.length() - base64.length() / 8 * 2 < 12 * 1024) { //小于12k，放大
                output = Thumbnails.of(output).scale(1.1f).asBufferedImage();
                base64 = decodeBase64String(output);
            }
            return decodeBase64String(output);
        } catch (Exception e) {
            return base64Img;
        }
    }

    public static BufferedImage base64ToBufferedImage(String base64string) {
        BufferedImage image = null;
        Base64 decoder = new Base64();
        try {
            byte[] bytes1 = decoder.decode(base64string);
            InputStream stream = new ByteArrayInputStream(bytes1);
            image = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static String decodeBase64String(BufferedImage bufferedImage) {
        Base64 encoder = new Base64();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (IOException e) {
            throw new SavageException("图片转化失败");
        }
        return new String(encoder.encode((baos.toByteArray())));
    }

}


