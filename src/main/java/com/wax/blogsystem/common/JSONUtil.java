package com.wax.blogsystem.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {

    public static String success(String msg){
        return result(0,null,0,msg);
    }

    public static String layUITable(List<?> list,int count){
        return result(0,list,count,null);
    }


    public static String result(int num, Object data,int count, String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", num);
        map.put("data", data);
        map.put("count",count);
        map.put("msg", msg);
        return JSON.toJSONString(map);
    }
}
