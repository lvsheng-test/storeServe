package org.pack.store.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.pack.store.entity.DictEntity;
import org.pack.store.enums.ResultEnums;
import org.pack.store.mapper.DictMapper;
import org.pack.store.requestVo.DictReq;
import org.pack.store.resposeVo.DictRes;
import org.pack.store.service.DictService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("dictService")
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper DictMapper;

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
    public void inserDictInfo(DictEntity dictInfo){
        DictMapper.inserDictInfo(dictInfo);
    }
}
