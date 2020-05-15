package com.moonlight035.udfsbackend.controller;

import com.moonlight035.udfsbackend.service.FileUploadService;
import com.moonlight035.udfsbackend.utils.FileUtils;
import com.moonlight035.udfsbackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping("/upload")
    public Result uploadFile(HttpServletRequest request, int code) throws IOException {
        try {
            if(request instanceof MultipartHttpServletRequest) {
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                fileUploadService.uploadFile(multipartHttpServletRequest,code);
                return Result.success(true);
            }
            return Result.fail(500,"无法获取文件请求");
        } catch (Exception e) {
            return Result.fail(500,e.getMessage());
        }
    }
}
