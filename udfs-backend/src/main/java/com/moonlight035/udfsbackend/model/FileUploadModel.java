package com.moonlight035.udfsbackend.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileUploadModel extends BaseModel implements Serializable {
    private String videoNo;
    private String saveName;
    private String name;
    private String suffix;
    private String url;
    private Integer type;
}
