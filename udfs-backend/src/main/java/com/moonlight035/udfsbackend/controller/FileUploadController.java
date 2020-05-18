package com.moonlight035.udfsbackend.controller;

import com.moonlight035.udfsbackend.model.FileUploadModel;
import com.moonlight035.udfsbackend.model.FileUploadQuery;
import com.moonlight035.udfsbackend.model.PageModel;
import com.moonlight035.udfsbackend.service.FileUploadService;
import com.moonlight035.udfsbackend.utils.FileUtils;
import com.moonlight035.udfsbackend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping("/upload")
    public Result uploadFile(HttpServletRequest request, HttpServletResponse response, Integer partition) throws IOException {
        if(partition==null){
            return Result.fail(500,"请选择分区");
        }
        try {
            if(request instanceof MultipartHttpServletRequest) {
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                fileUploadService.uploadFile(multipartHttpServletRequest,partition);
                return Result.success(true);
            }
            return Result.fail(500,"无法获取文件请求");
        } catch (Exception e) {
            log.error("上传文件失败",e);
            return Result.fail(500,e.getMessage());
        }
    }

    @RequestMapping("/download")
    public void downloadFile(String saveName, String suffix, int partition, HttpServletResponse response) throws IOException {
        try {
            byte[] bytes = fileUploadService.downloadFile(saveName+suffix, partition);
            FileUploadQuery query = new FileUploadQuery();
            query.setSaveName(saveName);
            FileUploadModel uploadModel = fileUploadService.selectOne(query);
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + uploadModel.getName()+uploadModel.getSuffix());
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.close();
        } catch (Exception e) {
            log.error("下载文件失败",e);
        }
    }

    @RequestMapping("/query")
    public Result queryFile(FileUploadQuery query){
        try {
            PageModel pageModel = fileUploadService.select(query);
            return Result.success(pageModel);
        } catch (Exception e) {
            log.error("服务异常",e);
            return Result.fail(500,"服务异常");
        }
    }
}
