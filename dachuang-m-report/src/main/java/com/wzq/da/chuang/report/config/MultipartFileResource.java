package com.wzq.da.chuang.report.config;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 参考：
 * https://github.com/spring-projects/spring-framework/issues/18147
 *
 * @author May
 * @since 2020/1/15 15:32
 */
public class MultipartFileResource extends ByteArrayResource {

    private String filename;

    public MultipartFileResource(MultipartFile multipartFile) throws IOException {
        super(multipartFile.getBytes());
        this.filename = multipartFile.getOriginalFilename();
    }

    @Override
    public String getFilename() {
        return this.filename;
    }
}
