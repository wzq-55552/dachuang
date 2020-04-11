package com.wzq.da.chuang.report.controller;

import com.wzq.da.chuang.commons.dto.ResponseResult;
import com.wzq.da.chuang.model.dto.report.ReportInsertParam;
import com.wzq.da.chuang.model.dto.report.ReportUpdateParam;
import com.wzq.da.chuang.model.pojos.report.MFile;
import com.wzq.da.chuang.model.pojos.report.MReport;
import com.wzq.da.chuang.model.pojos.report.Project;
import com.wzq.da.chuang.model.pojos.report.TimeArgs;
import com.wzq.da.chuang.report.config.MultipartFileResource;
import com.wzq.da.chuang.report.service.MFileService;
import com.wzq.da.chuang.report.service.MReportService;
import com.wzq.da.chuang.report.service.ProjectService;
import com.wzq.da.chuang.report.service.TimeArgsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.record.DVALRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/report/student")
@Api(tags = "中期报告学生操作内容controller")
public class StudentController {

    @Resource
    private MReportService mReportService;

    @Resource
    private MFileService mFileService;

    @Resource
    private ProjectService projectService;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private TimeArgsService timeArgsService;

    //学生组   ////////////////////////////////////////////////////

    //中期报告生成

    /**
     * 报告信息上传
     * @param reportInsertParam
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation(value = "中期报告信息生成")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Long> reportInsert(@RequestBody ReportInsertParam reportInsertParam){
        if (reportInsertParam != null && !StringUtils.isEmpty(reportInsertParam.getUserId())
        && !StringUtils.isEmpty(reportInsertParam.getProjectId())){

            if (date()){
                return new ResponseResult<Long>(ResponseResult.CodeStatus.FAIL,"中期报告修改时间过期，无法提交",null);
            }

            if (mReportService.selectByProjectId(reportInsertParam.getProjectId())!=null){
                return new ResponseResult<Long>(ResponseResult.CodeStatus.FAIL,"该中期报告已存在",null);
            }
            MReport mReport = new MReport();
            BeanUtils.copyProperties(reportInsertParam,mReport);
            int i = mReportService.insertSelective(mReport);
            if (i>0){
                MReport mReport1 = mReportService.selectByProjectId(reportInsertParam.getProjectId());
                //设置项目的中期报告已提交
                Project project = new Project();
                project.setProjectId(reportInsertParam.getProjectId());
                project.setMReport(true);
                projectService.updateByPrimaryKeySelective(project);
                return new ResponseResult<Long>(ResponseResult.CodeStatus.OK,"生成中期报告成功",mReport1.getReportId());
            }
            return new ResponseResult<Long>(ResponseResult.CodeStatus.FAIL,"生成中期报告失败",null);
        }
        return new ResponseResult<Long>(ResponseResult.CodeStatus.FAIL,"参数不足",null);
    }

    /**
     * 文件上传，可多个
     * @param files
     * @return
     */
    @PostMapping("/file/insert")
    @ApiOperation(value = "文件上传，可多个，在中期报告信息生成后发送，如果是修改中期报告，重新上传文件也是这个接口")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Void> fileInsert(@RequestParam("files") MultipartFile[] files,@RequestParam("reportId") Long reportId) throws IOException {
        if (files != null && files.length > 0 && !StringUtils.isEmpty(reportId)){

            if (date()){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"中期报告修改时间过期，无法提交");
            }

            for (MultipartFile file : files) {

                // 1 body
                //方法一：ByteArrayResource
                ByteArrayResource resource = new MultipartFileResource(file);
                // 方法二：接受到文件流后先暂时持久化到本地临时文件夹，然后转发,本地生成临时文件的，最好不使用
                //FileSystemResource resource = new FileSystemResource(new File("文件本地磁盘路径"));
                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
                body.add("file", resource);

                //2、 headers
                HttpHeaders headers = new HttpHeaders();

                //设置 接受的类型
                ArrayList<MediaType> acceptMediaTypes = new ArrayList<>();
                acceptMediaTypes.add(MediaType.APPLICATION_JSON);

                //2.1 设置header 等效 headers.add("Accept", APPLICATION_JSON.toString());
                headers.setAccept(acceptMediaTypes);

                //2.2 设置header 是表单提交 等效
                //headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                headers.setContentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8"));

                //3、构造请求体 body+header
                HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);

                //4、发送请求
                ResponseResult responseResult = restTemplate.postForObject("http://localhost:9002/upload", requestEntity ,ResponseResult.class);

                if (responseResult == null){
                    return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"文件上传失败");
                }
                System.out.println(responseResult.getCode()+":"+ResponseResult.CodeStatus.OK);
                if (!responseResult.getCode().equals(ResponseResult.CodeStatus.OK)){
                    return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"文件上传失败");
                }
                String url = (String) responseResult.getData();
                MFile mFile = new MFile();
                mFile.setFUrl(url);
                mFile.setReportId(reportId);
                mFile.setFName(file.getOriginalFilename());//文件名
                mFileService.insertSelective(mFile);
            }
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"文件上传成功");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"参数不足");
    }

    /**
     * 文件上传，一个
     * @param file
     * @return
     */
    @PostMapping("/file/insert/one")
    @ApiOperation(value = "文件上传，一个一个，我不知道前端要怎么传，多个文件的方法也有，单个文件的话，是这个")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Void> fileInsertOne(@RequestParam("file") MultipartFile file,@RequestParam("reportId") Long reportId) throws IOException {
        if (file != null && !StringUtils.isEmpty(reportId)){

            if (date()){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"中期报告修改时间过期，无法提交");
            }

            // 1 body
            //方法一：ByteArrayResource
            ByteArrayResource resource = new MultipartFileResource(file);
            // 方法二：接受到文件流后先暂时持久化到本地临时文件夹，然后转发,本地生成临时文件的，最好不使用
            //FileSystemResource resource = new FileSystemResource(new File("文件本地磁盘路径"));
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", resource);

            //2、 headers
            HttpHeaders headers = new HttpHeaders();

            //设置 接受的类型
            ArrayList<MediaType> acceptMediaTypes = new ArrayList<>();
            acceptMediaTypes.add(MediaType.APPLICATION_JSON);

            //2.1 设置header 等效 headers.add("Accept", APPLICATION_JSON.toString());
            headers.setAccept(acceptMediaTypes);

            //2.2 设置header 是表单提交 等效
            //headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.setContentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8"));

            //3、构造请求体 body+header
            HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);

            //4、发送请求
            ResponseResult responseResult = restTemplate.postForObject("http://localhost:9002/upload", requestEntity ,ResponseResult.class);

            if (responseResult == null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"文件上传失败");
            }
            System.out.println(responseResult.getCode()+":"+ResponseResult.CodeStatus.OK);
            if (!responseResult.getCode().equals(ResponseResult.CodeStatus.OK)){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"文件上传失败");
            }
            String url = (String) responseResult.getData();
            MFile mFile = new MFile();
            mFile.setFUrl(url);
            mFile.setReportId(reportId);
            mFile.setFName(file.getOriginalFilename());//文件名
            mFileService.insertSelective(mFile);

            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"文件上传成功");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"参数不足");
    }

    //中期报告修改

    /**
     * 修改中期报告,其实也就简介修改了
     * @param reportUpdateParam
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改中期报告,其实也就修改简介而已了，传id和content过来")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Void> reportUpdate(@RequestBody ReportUpdateParam reportUpdateParam){
        if (reportUpdateParam!=null && reportUpdateParam.getReportId()!=null){

            if (date()){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"中期报告修改时间过期，无法修改");
            }

            //导师通过
            if (mReportService.selectByPrimaryKey(reportUpdateParam.getReportId()).getTApproval().equals(2)){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"中期报告导师已通过，无法修改");
            }

            MReport mReport = new MReport();
            BeanUtils.copyProperties(reportUpdateParam,mReport);
            int i = mReportService.updateByPrimaryKeySelective(mReport);
            if (i>0){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"修改成功");
            }
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"修改失败");
    }

    /**
     * 文件删除
     * @param fileId
     * @return
     */
    @PostMapping("/file/delete")
    @ApiOperation(value = "文件删除，一个一个删，点击文件后的删除按钮，就删除了对应该中期报告的一个文件，给我fileId")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Void> fileDelete(@RequestBody Map<String,String> fileId) {
        if (fileId!=null && !StringUtils.isEmpty(fileId.get("fileId"))){

            if (date()){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"中期报告修改时间过期，无法修改");
            }

            String id = fileId.get("fileId");
            MFile mFile = mFileService.selectByPrimaryKey(Long.valueOf(id));

            if (mFile == null || StringUtils.isEmpty(mFile.getFUrl())){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"文件不存在");
            }

            MReport mReport = mReportService.selectByPrimaryKey(mFile.getReportId());
            if (mReport.getTApproval().equals(2)){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"中期报告导师已通过，无法修改");
            }

            String fUrl = mFile.getFUrl();
            //删除文件
            Map<String, String> map = new HashMap<>();
            map.put("url",fUrl);
            ResponseResult responseResult = restTemplate.postForObject("http://localhost:9002/delete", map, ResponseResult.class);
            if (responseResult!=null && responseResult.getCode().equals(ResponseResult.CodeStatus.OK)) {
                //删除数据库纪录
                mFileService.deleteByPrimaryKey(id);
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"删除成功");
            }
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"删除失败");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"参数为空");
    }

    public boolean date(){
        TimeArgs timeArgs = timeArgsService.select();
        if (timeArgs.getMOutDate().before(new Date())){
            return true;
        }
        return false;
    }

}
