package com.moonlight035.udfsbackend.service;

import com.moonlight035.udfsbackend.constants.FilePartitionEnum;
import com.moonlight035.udfsbackend.dao.FileUploadDao;
import com.moonlight035.udfsbackend.model.FileUploadModel;
import com.moonlight035.udfsbackend.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.Optional;

@Service
public class FileUploadService {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private FileUploadDao fileUploadDao;

    public void uploadFile(MultipartHttpServletRequest request,int code) throws IOException {
        MultipartFile uploadFile = request.getFileMap().values().stream().findFirst().get();
        String suffix = uploadFile.getName().substring(uploadFile.getName().indexOf(File.separator));
        String saveName = FileUtils.buildName();
        File file = new File(uploadPath+File.pathSeparator+ FilePartitionEnum.getEnumByCode(code).getPath()
                +File.pathSeparator+saveName+File.separator+suffix);
        if(file.exists()||!file.mkdirs()) throw new RuntimeException("创建文件失败");
        FileOutputStream outputStream = new FileOutputStream(file);
        InputStream inputStream = uploadFile.getInputStream();
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        outputStream.write(bytes);
        inputStream.close();
        outputStream.close();
    }
}
