package com.wzxy.uavfilingsystem.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    //分页默认settings
    private static int PAGE_SIZE=20;
    private static int PAGE_NUM=1;
    private int PageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;

    private HashMap param=new HashMap();
}
