package com.moonlight035.udfsbackend.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseModel implements Serializable {
    private String createAccount;
    private Date createTime;
    private String modifyAccount;
    private Date modifyTime;
    private String remark;
}
