package com.wzq.da.chuang.file.test;

import com.wzq.da.chuang.file.utils.FastDFSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FileTest {

    @Test
    public void Upload() {
        String fileUrl = this.getClass().getResource("/test.png").getPath();
        File file = new File(fileUrl);
        String str = FastDFSClient.uploadFile(file);
        FastDFSClient.getResAccessUrl(str);
    }


}
