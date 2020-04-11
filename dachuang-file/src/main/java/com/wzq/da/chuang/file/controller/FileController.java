package com.wzq.da.chuang.file.controller;

import com.wzq.da.chuang.commons.dto.ResponseResult;
import com.wzq.da.chuang.file.service.impl.FileService;
import com.wzq.da.chuang.file.utils.FastDFSClient;
import com.wzq.da.chuang.model.dto.file.DownloadDto;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FileController {

    @Resource
    private FileService fileService;

    /***
     * 文件上传
     * @return
     */
    @PostMapping(value = "/upload")
    public ResponseResult<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file != null){
            return fileService.upload(file);
        }
        return new ResponseResult<String>(ResponseResult.CodeStatus.FAIL,"文件为空",null);
    }

    /***
     * 文件删除
     * @return
     */
    @PostMapping(value = "/delete")
    public ResponseResult<Void> delete(@RequestBody Map<String,String> url) throws Exception {
        if (!StringUtils.isEmpty(url.get("fileUrl"))){
            //去掉文件路径前缀
            String fileUrl = DeleteFrontUrl(url.get("fileUrl"));
            return fileService.delete(fileUrl);
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"文件url为空");
    }

    /***
     * 文件下载
     * @return
     */
    @PostMapping(value = "/download")
    public ResponseResult<ByteArrayInputStream> download(@RequestBody DownloadDto downloadDto, HttpServletResponse response) throws Exception {
        if (downloadDto!=null && !StringUtils.isEmpty(downloadDto.getFileName())
        && !StringUtils.isEmpty(downloadDto.getFileUrl()) ){
            try {
                //去掉文件路径前缀
                String fileUrl = DeleteFrontUrl(downloadDto.getFileUrl());
                byte[] bytes = fileService.download(fileUrl);
                // 需要在上传的时候保存文件名。下载的时候使用对应的格式,消费者传名字（包括后缀）和路径过来，路径去掉前缀
                response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(downloadDto.getFileName(), "UTF-8"));
                response.setCharacterEncoding("UTF-8");
                ServletOutputStream outputStream = null;
                try {
                    outputStream = response.getOutputStream();
                    outputStream.write(bytes);
                    return new ResponseResult<ByteArrayInputStream>(ResponseResult.CodeStatus.OK,"下载成功",null);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert outputStream != null;
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return new ResponseResult<ByteArrayInputStream>(ResponseResult.CodeStatus.FAIL,"服务器内部错误",null);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseResult<ByteArrayInputStream>(ResponseResult.CodeStatus.FAIL,"服务器内部错误",null);
            }
        }
        return new ResponseResult<ByteArrayInputStream>(ResponseResult.CodeStatus.FAIL,"文件参数不足",null);
    }

    //去掉文件路径前缀
    public static String DeleteFrontUrl(String fileUrl){
        fileUrl = fileUrl.replace(FastDFSClient.getFrontUrl(),"");
        return fileUrl;
    }

//    public static void main(String[] args) {
//        String s = DeleteFrontUrl("http://47.113.80.250/group1/M00/00/00/rBJg-l6NpqKAT-baAATuTCSFI4s96.docx");
//        System.out.println(s);
//    }

}
