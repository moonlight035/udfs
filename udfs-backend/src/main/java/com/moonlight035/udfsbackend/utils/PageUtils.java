package com.moonlight035.udfsbackend.utils;

import com.moonlight035.udfsbackend.model.PageModel;

import java.util.List;

/**
 * @author jing.liu14@ucarinc.com
 * @date 2020/5/18
 * @description:
 */
public class PageUtils {
    public static PageModel buildPage(int pageIndex, int pageSize, int total, List dataList){
        PageModel pageModel = new PageModel();
        pageModel.setDataList(dataList);
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(pageSize);
        pageModel.setTotal(total);
        return pageModel;
    }
}
