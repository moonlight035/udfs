package com.moonlight035.udfsbackend.dao;

import com.moonlight035.udfsbackend.model.FileUploadModel;
import com.moonlight035.udfsbackend.model.FileUploadQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileUploadDao {

    int insert(FileUploadModel model);

    List<FileUploadModel> select(FileUploadQuery fileUploadQuery);

    int selectCount(FileUploadQuery fileUploadQuery);

    FileUploadModel selectOne(FileUploadQuery fileUploadQuery);
}
