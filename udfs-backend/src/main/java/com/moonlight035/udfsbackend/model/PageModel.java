package com.moonlight035.udfsbackend.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author jing.liu14@ucarinc.com
 * @date 2020/5/18
 * @description:
 */
@Data
public class PageModel implements Serializable {

    private Integer total;
    private Integer pageIndex;
    private Integer pageSize;
    private List dataList;
}
