package com.moonlight035.udfsbackend.model;

import lombok.Data;

/**
 * @author jing.liu14@ucarinc.com
 * @date 2020/5/18
 * @description:
 */
@Data
public class BaseQuery {
    private Integer pageSize;
    private Integer pageIndex;
    private Integer pageStart;

    public Integer getPageStart(){
        return (pageIndex-1)*pageSize;
    }
}
