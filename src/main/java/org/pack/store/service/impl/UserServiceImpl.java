package org.pack.store.service.impl;


import org.pack.store.entity.AddressEntity;
import org.pack.store.enums.ResultEnums;
import org.pack.store.mapper.AddressMapper;
import org.pack.store.requestVo.AddressReq;
import org.pack.store.service.UserService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.StringUtil;
import org.pack.store.utils.common.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private AddressMapper addressMapper;

    public AppletResult queryMyAddress(String userId){
        if (StringUtil.isNullStr(userId)){
            return ResultUtil.error(ResultEnums.USERID_IS_NULL);
        }
        List<AddressEntity> addressList = addressMapper.queryMyAddress(userId);
        return ResultUtil.success(addressList);
    }

    public AppletResult updateMyAddress(AddressReq addressReq){
        try{
            AddressEntity addressInfo = addressMapper.getById(addressReq.getId());
            if (addressInfo ==null){
                return ResultUtil.error(ResultEnums.NOT_FOUND_DATA);
            }
            addressMapper.updateMyAddress(addressReq);
        }catch (Exception e){
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
        return ResultUtil.success();
    }

    public AppletResult insertMyAddress(AddressReq addressReq){
        AddressEntity addressInfo =new AddressEntity();
        addressInfo.setId(UuidUtil.getUuid());
        addressInfo.setUserId(addressReq.getUserId());
        addressInfo.setContact(addressReq.getContact());
        addressInfo.setSex(addressReq.getSex());
        addressInfo.setPhone(addressReq.getPhone());
        addressInfo.setProvin(addressReq.getProvin());
        addressInfo.setCity(addressReq.getCity());
        addressInfo.setArea(addressReq.getArea());
        addressInfo.setAddress(addressReq.getAddress());
        addressInfo.setDefaultAddress(addressReq.getDefaultAddress());
        addressInfo.setTs(new Date());
        addressMapper.insertMyAddress(addressInfo);
        return ResultUtil.success();
    }
}
