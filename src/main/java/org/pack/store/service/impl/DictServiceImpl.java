package org.pack.store.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.pack.store.entity.ConfigProportionEntity;
import org.pack.store.entity.DictEntity;
import org.pack.store.entity.QuestionsEntity;
import org.pack.store.enums.ConfigEnums;
import org.pack.store.enums.ResultEnums;
import org.pack.store.mapper.ConfigProportionMapper;
import org.pack.store.mapper.DictMapper;
import org.pack.store.mapper.QuestionsMapper;
import org.pack.store.requestVo.*;
import org.pack.store.resposeVo.DictRes;
import org.pack.store.service.DictService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.JSONResult;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.common.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;


@Service("dictService")
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper DictMapper;

    @Autowired
    private ConfigProportionMapper configProportionMapper;

    @Autowired
    private QuestionsMapper questionsMapper;

    public AppletResult queryDictAll(DictReq dictReq){
        DictRes dictRes =new DictRes();
        PageHelper.startPage(dictReq.getCurrentPage(),8,true);
        List<DictEntity> dictList = DictMapper.queryDictAll(dictReq.getParentCode());
        PageInfo<DictEntity> page =new PageInfo<DictEntity>(dictList);
        if (dictList.size()>0){
            dictRes.setPageTotal(page.getPages());
        }else {
            dictRes.setPageTotal(0);
        }
        dictRes.setDictList(dictList);
        return ResultUtil.success(dictRes);
    }

    public AppletResult searchParentCode(DictByParentCodeReq dictByParentCodeReq){
        DictRes dictRes =new DictRes();
        List<DictEntity> dictList = DictMapper.queryDictAll(dictByParentCodeReq.getParentCode());
        dictRes.setPageTotal(dictList.size());
        dictRes.setDictList(dictList);
        return ResultUtil.success(dictRes);
    }

    public JSONResult queryConfigAll(ConfigReq configReq){
        PageHelper.startPage(configReq.getPage(),configReq.getLimit(),true);
        List<ConfigProportionEntity> configList = configProportionMapper.selectAll();
        return ResultUtil.success(configList,configList.size());
    }

    public AppletResult addQuestionsInfo(QuestionsReq questionsReq){
        QuestionsEntity QuestionsEntity =new QuestionsEntity();
        QuestionsEntity.setId(UuidUtil.getUuid());
        QuestionsEntity.setTitle(questionsReq.getTitle());
        QuestionsEntity.setContent(questionsReq.getContent());
        int i = questionsMapper.addQuestionsInfo(QuestionsEntity);
        if (i==0){
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
        return ResultUtil.success();
    }

    public void inserDictInfo(DictEntity dictInfo){
        DictMapper.inserDictInfo(dictInfo);
    }

    public void deleteDictInfo(String id){
        DictMapper.deleteDictInfo(id);
    }

    public void insertConfigProportion(ConfigAddReq configAddReq){
        BigDecimal a =null;
        Integer proportion =Integer.parseInt(configAddReq.getProportion());
        a = BigDecimal.valueOf(proportion.doubleValue()/100);
        BigDecimal  b =a.setScale(2, RoundingMode.HALF_UP);//保留两位小数
        ConfigProportionEntity configProportion=new ConfigProportionEntity();
        configProportion.setId(UuidUtil.getUuid());
        configProportion.setType(configAddReq.getType());
        configProportion.setName(ConfigEnums.getMessage(configAddReq.getType()));
        configProportion.setProportion(b);
        configProportion.setTs(new Date());
        configProportionMapper.insertConfigProportion(configProportion);
    }
}
