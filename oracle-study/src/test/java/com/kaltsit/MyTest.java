package com.kaltsit;

import com.kaltsit.abstracts.Photo;
import com.kaltsit.mapper.MyMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.kaltsit.mapper.HyjfJbxxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;

@Slf4j
@SpringBootTest
@ContextConfiguration
public class MyTest {
    @Autowired
    private HyjfJbxxMapper hyjfJbxxMapper;
    @Autowired
    private MyMapper myMapper;

    @Test
    void myTest1() {
        hyjfJbxxMapper.selectById("1714518493372596226");
    }

    @Test
    void myTest2() {
        downloadPhotos("2000", 0, 10);
    }

    /**
     * 导出照片
     *
     * @param csn   出生年
     * @param xb    性别 0-女，1-男
     * @param count 照片数量
     */
    void downloadPhotos(String csn, Integer xb, Integer count) {
        String path = "C:\\Users\\20201236\\Desktop\\照片\\" + csn + "-" + xb;
        String xbnum = "";
        if (xb == 1) xbnum = "'1','3','5','7','9'";
        if (xb == 0) xbnum = "'2','4','6','8','0'";
        List<Photo> list = myMapper.getPhotos(csn, xbnum, count);
        try {
            File file = new File(path);
            // 判断文件夹路径，没有则创建
            if (!file.exists()) {
                file.mkdirs();
            }
            for (Photo photo : list) {
                byte[] zp = photo.getZp();
                OutputStream out = Files.newOutputStream(new File(path + "\\" + photo.getNum() + "-" + photo.getName() + ".jpg").toPath());
                out.write(zp);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
