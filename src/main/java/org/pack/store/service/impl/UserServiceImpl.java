package org.pack.store.service.impl;


import org.pack.store.entity.AddressEntity;
import org.pack.store.entity.DictEntity;
import org.pack.store.enums.DictEnums;
import org.pack.store.enums.ResultEnums;
import org.pack.store.mapper.AddressMapper;
import org.pack.store.mapper.DictMapper;
import org.pack.store.requestVo.AddressReq;
import org.pack.store.requestVo.ParentCodeReq;
import org.pack.store.resposeVo.CityInfoRes;
import org.pack.store.service.UserService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.StringUtil;
import org.pack.store.utils.common.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private DictMapper dictMapper;

    public AppletResult queryMyAddress(String userId){
        if (StringUtil.isNullStr(userId)){
            return ResultUtil.error(ResultEnums.USERID_IS_NULL);
        }
        List<AddressEntity> addressList = addressMapper.queryMyAddress(userId);
        return ResultUtil.success(addressList);
    }

    public AppletResult updateMyAddress(AddressReq addressReq){
        try{
            if (StringUtil.isNullStr(addressReq.getId())){
                return ResultUtil.error(ResultEnums.ID_IS_NULL);
            }
            if (StringUtil.isNullStr(addressReq.getUserId())){
                return ResultUtil.error(ResultEnums.USERID_IS_NULL);
            }
            AddressEntity addressInfo = addressMapper.getById(addressReq.getId());
            if (addressInfo ==null){
                return ResultUtil.error(ResultEnums.NOT_FOUND_DATA);
            }
            //修改之前先校验本次是否修改默认地址，如果设置默认地址，先把原来默认地址取消掉，本次设置默认地址
            if (!StringUtil.isNullStr(addressReq.getDefaultAddress())){
                if (addressReq.getDefaultAddress()=='Y'){
                    AddressEntity address = addressMapper.queryAdressByUserIdAndDefalust(addressReq.getUserId());
                    if (address !=null){
                        address.setDefaultAddress('N');
                        addressMapper.updateAddress(address);
                    }
                }
            }
            addressMapper.updateMyAddress(addressReq);
        }catch (Exception e){
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
        return ResultUtil.success();
    }

    public AppletResult insertMyAddress(AddressReq addressReq){
        if (StringUtil.isNullStr(addressReq.getUserId())){
            return ResultUtil.error(ResultEnums.USERID_IS_NULL);
        }
        if (!StringUtil.isNullStr(addressReq.getDefaultAddress())){
            if (addressReq.getDefaultAddress()=='Y'){
                AddressEntity address = addressMapper.queryAdressByUserIdAndDefalust(addressReq.getUserId());
                if (address !=null){
                    address.setDefaultAddress('N');
                    addressMapper.updateAddress(address);
                }
            }
        }
        AddressEntity addressInfo =new AddressEntity();
        addressInfo.setId(UuidUtil.getUuid());
        addressInfo.setUserId(addressReq.getUserId());
        addressInfo.setContact(addressReq.getContact());
        addressInfo.setSex(addressReq.getSex());
        addressInfo.setPhone(addressReq.getPhone());
        addressInfo.setCityCode(addressReq.getCityCode());
        addressInfo.setCityName(addressReq.getCityName());
        addressInfo.setAddress(addressReq.getAddress());
        addressInfo.setDefaultAddress(addressReq.getDefaultAddress());
        addressInfo.setTs(new Date());
        addressMapper.insertMyAddress(addressInfo);
        return ResultUtil.success();
    }

    public AppletResult queryCommonDict(ParentCodeReq parentCodeReq){
        List<CityInfoRes> list = dictMapper.queryCommonParentCode(parentCodeReq.getParentCode());
        return ResultUtil.success(list);
    }
}
