package org.pack.store.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JSONResult implements Serializable{

    private static final long serialVersionUID = 8360101072462967795L;

     /** 返回码 **/
     Integer code;

     /** msg **/
     String msg;
     /** 总页数 **/
     Integer count;

     /** 业务返回具体信息 **/
     List<?> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 转换成实体对象
     * @param tClass
     * @return
     */
    public static <T> T parseToObject(Object data,Class<T> tClass){
        if (data!=null){
            return  JSONUtil.parseObject(JSONUtil.toJSONString(data),tClass);
        }
       return null;
    }
}
