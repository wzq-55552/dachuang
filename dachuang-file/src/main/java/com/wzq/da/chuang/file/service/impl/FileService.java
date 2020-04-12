package com.wzq.da.chuang.file.service.impl;

import com.wzq.da.chuang.commons.dto.ResponseResult;
import com.wzq.da.chuang.file.utils.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileService {

    public ResponseResult<String> upload(MultipartFile multipartFile) {
        // 上传时的文件名全部
        String originFileName = multipartFile.getOriginalFilename();
        // 后缀
        String extName = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        // 判断是不是符合要求的文件后缀
        if(!extName.matches("(png|jpg|jpeg|doc|docx|pdf|pptx|xls|xlsx|txt|md)")) {
            // "文件格式错误"
            return new ResponseResult<String>(ResponseResult.CodeStatus.FAIL,"文件格式错误",null);
        }
        String fileId = null;
        //上传图片获得文件id
        try {
            // 上传到fastDFSClient
            fileId = FastDFSClient.uploadFile(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            // "服务器内部问题"
            return new ResponseResult<String>(ResponseResult.CodeStatus.FAIL,"服务器内部问题",null);
        }
        fileId = FastDFSClient.getFrontUrl()+fileId;
        return new ResponseResult<String>(ResponseResult.CodeStatus.OK,"上传文件成功",fileId);
    }


    public ResponseResult<Void> delete(String fileUrl) throws Exception {
        try {
            // 传入图片id
            boolean b = FastDFSClient.deleteFile(fileUrl);
            if (b){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"删除文件成功");
            }
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"服务器内部问题");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"服务器内部问题");
        }
    }

    public byte[] download(String fileUrl) throws Exception {
        return FastDFSClient.downloadFile(fileUrl);
    }
}
