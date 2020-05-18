package com.moonlight035.udfsbackend.model;

import lombok.Data;

/**
 * @author jing.liu14@ucarinc.com
 * @date 2020/5/18
 * @description:
 */
@Data
public class FileUploadQuery extends BaseQuery{
    private Long id;
    private Integer partition;
    private Integer type;
    private String saveName;
    private String name;
}
