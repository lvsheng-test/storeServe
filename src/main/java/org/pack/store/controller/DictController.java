package org.pack.store.controller;

import io.swagger.annotations.ApiOperation;
import org.pack.store.entity.DictEntity;
import org.pack.store.enums.DictEnums;
import org.pack.store.requestVo.DictByIdReq;
import org.pack.store.requestVo.DictByParentCodeReq;
import org.pack.store.requestVo.DictReq;
import org.pack.store.service.DictService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.common.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.*;


@RestController
@RequestMapping(value = "/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @CrossOrigin
    @ApiOperation(value = "字典信息查询")
    @PostMapping(value = "queryAll")
    public AppletResult queryDictAll(@RequestBody DictReq dictReq){
        System.out.print("**********************************进来");
        return dictService.queryDictAll(dictReq);
    }

    @CrossOrigin
    @ApiOperation(value = "根据父级编码查询字典信息")
    @PostMapping(value = "searchParentCode")
    public AppletResult searchParentCode(@RequestBody DictByParentCodeReq dictByParentCodeReq){
        return dictService.searchParentCode(dictByParentCodeReq);
    }



    @CrossOrigin
    @ApiOperation(value = "字典父级编码查询")
    @GetMapping(value = "getDictParentCode")
    public AppletResult getDictParentCode(){
        List<Map<String, Object>> lsit= new ArrayList<Map<String, Object>>();
        try{
            lsit =getAllEnum("org.pack.store.enums.DictEnums");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.success(lsit);
    }

    @CrossOrigin
    @ApiOperation(value = "添加字典信息")
    @PostMapping(value = "inserDictInfo")
    public AppletResult inserDictInfo(@RequestBody DictEntity dictInfo){
        dictInfo.setId(UuidUtil.getUuid());
        dictInfo.setCode(UuidUtil.getDictCode());
        dictInfo.setTs(new Date());
        dictInfo.setParentName(DictEnums.getMessage(dictInfo.getParentCode()));
        dictService.inserDictInfo(dictInfo);
        return ResultUtil.success();
    }

    @CrossOrigin
    @ApiOperation(value = "删除字典信息")
    @PostMapping(value = "deleteDictInfo")
    public AppletResult deleteDictInfo(@RequestBody DictByIdReq dictByIdReq){
        System.out.print("传参数:"+dictByIdReq.getId());
        String [] ids = dictByIdReq.getId().split(",");
        System.out.print("" +
                ":"+ids[0]);
        for(String id :ids){
            dictService.deleteDictInfo(id);
        }
        return ResultUtil.success();
    }



    /**
     * 根据枚举的字符串获取枚举的值
     *
     * @param className 包名+类名
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> getAllEnum(String className) throws Exception {
        // 得到枚举类对象
        Class<Enum> clazz = (Class<Enum>) Class.forName(className);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //获取所有枚举实例
        Enum[] enumConstants = clazz.getEnumConstants();
        //根据方法名获取方法
        Method getCode = clazz.getMethod("getCode");
        Method getMessage = clazz.getMethod("getMessage");
        Map<String, Object> map = null;
        for (Enum enum1 : enumConstants) {
            map = new HashMap<String, Object>();
            //执行枚举方法获得枚举实例对应的值
            map.put("code", getCode.invoke(enum1));
            map.put("message", getMessage.invoke(enum1));
            list.add(map);
        }
        return list;
    }

}
