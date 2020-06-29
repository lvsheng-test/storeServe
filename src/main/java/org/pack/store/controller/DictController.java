package org.pack.store.controller;

import io.swagger.annotations.ApiOperation;
import org.pack.store.requestVo.DictReq;
import org.pack.store.service.DictService;
import org.pack.store.utils.AppletResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @CrossOrigin
    @ApiOperation(value = "字典信息查询")
    @PostMapping(value = "queryAll")
    public AppletResult queryDictAll(@RequestBody DictReq dictReq){
        return dictService.queryDictAll(dictReq);
    }
}
