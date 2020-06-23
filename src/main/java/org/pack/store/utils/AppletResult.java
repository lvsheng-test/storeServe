package org.pack.store.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppletResult implements Serializable{

    private static final long serialVersionUID = 8360101072462967795L;

     /** 返回码 **/
     Integer code;

     /** msg **/
     String msg;

     /** 业务返回具体信息 **/
     Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
