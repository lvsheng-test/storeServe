package org.pack.store.controller;

import io.swagger.annotations.ApiOperation;
import org.pack.store.entity.DictEntity;
import org.pack.store.enums.DictEnums;
import org.pack.store.requestVo.*;
import org.pack.store.service.DictService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.JSONResult;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.common.EnumUtils;
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
            lsit = EnumUtils.getAllEnum("org.pack.store.enums.DictEnums");
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

    @CrossOrigin
    @ApiOperation(value = "添加配置信息")
    @PostMapping(value = "insertConfigProportion")
    public AppletResult insertConfigProportion(@RequestBody ConfigAddReq configAddReq){
        dictService.insertConfigProportion(configAddReq);
        return ResultUtil.success();
    }


    @CrossOrigin
    @ApiOperation(value = "查询配置比例信息列表")
    @PostMapping(value = "queryConfigAll")
    public JSONResult queryConfigAll(@RequestBody ConfigReq configReq){
        return dictService.queryConfigAll(configReq);
    }

    @CrossOrigin
    @ApiOperation(value = "查询配置类型")
    @GetMapping(value = "getConfigTYpe")
    public AppletResult getConfigTYpe(){
        List<Map<String, Object>> lsit= new ArrayList<Map<String, Object>>();
        try{
            lsit = EnumUtils.getAllEnum("org.pack.store.enums.ConfigEnums");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.success(lsit);
    }

    @CrossOrigin
    @ApiOperation(value = "添加常见问题信息")
    @PostMapping(value = "addQuestionsInfo")
    public AppletResult addQuestionsInfo(@RequestBody QuestionsReq questionsReq){
        return dictService.addQuestionsInfo(questionsReq);
    }



}
