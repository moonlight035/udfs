package com.moonlight035.udfsbackend.service;

import com.moonlight035.udfsbackend.constants.FilePartitionEnum;
import com.moonlight035.udfsbackend.dao.FileUploadDao;
import com.moonlight035.udfsbackend.model.FileUploadModel;
import com.moonlight035.udfsbackend.model.FileUploadQuery;
import com.moonlight035.udfsbackend.model.PageModel;
import com.moonlight035.udfsbackend.utils.FileUtils;
import com.moonlight035.udfsbackend.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.Date;
import java.util.List;

@Service
public class FileUploadService {

    private final char dot = '.';

    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private FileUploadDao fileUploadDao;

    public void uploadFile(MultipartHttpServletRequest request,int partition) throws IOException {
        MultipartFile uploadFile = request.getFileMap().values().stream().findFirst().get();
        int dotIndex = uploadFile.getOriginalFilename().lastIndexOf(dot);
        String suffix;
        if(dotIndex!=-1)
            suffix = uploadFile.getOriginalFilename().substring(dotIndex);
        else
            suffix = "";
        String saveName = FileUtils.buildName();
        File parent = new File(uploadPath+File.separator+ FilePartitionEnum.getEnumByCode(partition).getPath());
        if(!parent.exists()&&!parent.mkdirs()) throw new RuntimeException("创建目录失败");
        File file = new File(uploadPath+File.separator+ FilePartitionEnum.getEnumByCode(partition).getPath()
                +File.separator+saveName+suffix);
        if(!file.createNewFile()) throw new RuntimeException("创建文件失败");

        FileUploadModel uploadModel = new FileUploadModel();
        if(dotIndex!=-1)
            uploadModel.setName(uploadFile.getOriginalFilename().substring(0,dotIndex));
        else
            uploadModel.setName(uploadFile.getOriginalFilename());
        uploadModel.setSaveName(saveName);
        uploadModel.setSuffix(suffix);
        uploadModel.setType(FileUtils.getFileType(suffix).getCode());
        uploadModel.setPartition(partition);
        uploadModel.setCreateAccount("sys");
        uploadModel.setCreateTime(new Date());
        uploadModel.setModifyAccount("sys");
        uploadModel.setModifyTime(new Date());
        fileUploadDao.insert(uploadModel);
        FileOutputStream outputStream = new FileOutputStream(file);
        InputStream inputStream = uploadFile.getInputStream();
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        outputStream.write(bytes);
        inputStream.close();
        outputStream.close();
    }

    public byte[] downloadFile(String saveName, int partition) throws IOException {
        File file = new File(uploadPath+File.separator+ FilePartitionEnum.getEnumByCode(partition).getPath()
                +File.separator+saveName);
        if(!file.exists()) throw new RuntimeException("文件不存在");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        return bytes;
    }

    public PageModel select(FileUploadQuery fileUploadQuery){
        List<FileUploadModel> dataList = fileUploadDao.select(fileUploadQuery);
        int count = fileUploadDao.selectCount(fileUploadQuery);
        return PageUtils.buildPage(fileUploadQuery.getPageIndex(),fileUploadQuery.getPageSize()
                ,count,dataList);
    }

    public FileUploadModel selectOne(FileUploadQuery fileUploadQuery){
        return fileUploadDao.selectOne(fileUploadQuery);
    }

}
