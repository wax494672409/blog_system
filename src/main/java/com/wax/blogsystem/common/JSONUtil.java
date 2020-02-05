package com.wax.blogsystem.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {

    public static String success(){
        return result(true,null,null);
    }

    public static String success(String msg){
        return result(true,null,msg);
    }

    public static String error(String msg){
        return result(false,null,msg);
    }


    public static String layUITable(List<?> list,long count){
        return resultTable(0,list,count,null);
    }


    public static String result(boolean b,Object data,String msg){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", b);
        map.put("data", data);
        map.put("msg", msg);
        return JSON.toJSONString(map);
    }

    public static String resultTable(int num, Object data,long count, String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", num);
        map.put("data", data);
        map.put("count",count);
        map.put("msg", msg);
        return JSON.toJSONString(map);
    }
}
