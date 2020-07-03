package org.pack.store.utils;


import org.pack.store.enums.ResultEnums;
import org.springframework.stereotype.Component;

@Component
public class ResultUtil {
    public static AppletResult success(Object object){

        AppletResult result = new AppletResult();
        result.setCode(ResultEnums.RETURN_SUCCESS.getCode());
        result.setMsg(ResultEnums.RETURN_SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static AppletResult successV2(Object object){
        AppletResult result = new AppletResult();
        result.setCode(ResultEnums.RETURN_SUCCESS.getCode());
        result.setMsg(ResultEnums.RETURN_SUCCESS.getMsg());
        result.setData(new ResultBuild(object));
        return result;
    }

    public static AppletResult success(){
        AppletResult result = new AppletResult();
        result.setCode(ResultEnums.RETURN_SUCCESS.getCode());
        result.setMsg(ResultEnums.RETURN_SUCCESS.getMsg());
        result.setData(new ResultBuild());
        return result;
    }


    public static AppletResult success(ResultEnums resultEnums){
        AppletResult result = new AppletResult();
        result.setCode(ResultEnums.RETURN_SUCCESS.getCode());
        result.setMsg(resultEnums.getMsg());
        result.setData(new ResultBuild());
        return result;
    }


    public static AppletResult error(ResultEnums resultEnums,String...msg){
        AppletResult result = new AppletResult();
        result.setCode(resultEnums.getCode());
        if (msg.length>0){
            StringBuffer sb=new StringBuffer(msg[0]);
            result.setMsg(sb.append(resultEnums.getMsg()).toString());
        }else {
            result.setMsg(resultEnums.getMsg());
        }
        result.setData(new ResultBuild());
        return result;
    }

    public static AppletResult error(int code,String msg){
        AppletResult result = new AppletResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static AppletResult successv1(ResultEnums resultEnums,Object object){

        AppletResult result = new AppletResult();
        ReturnDataUtil res = new ReturnDataUtil();
        res.setReturnData(object);
        result.setCode(resultEnums.getCode());
        result.setMsg(resultEnums.getMsg());
        result.setData(res);
        return result;
    }

    public static AppletResult success3(ResultEnums resultEnums,Object object){
        AppletResult result = new AppletResult();
        result.setCode(resultEnums.getCode());
        result.setMsg(resultEnums.getMsg());
        result.setData(object);
        return result;
    }

    static class ResultBuild{

        private Object returnData;

        public ResultBuild() {
        }

        public ResultBuild(Object returnData) {
            this.returnData = returnData;
        }

        public Object getReturnData() {
            return returnData;
        }

        public void setReturnData(Object returnData) {
            this.returnData = returnData;
        }
    }


}
