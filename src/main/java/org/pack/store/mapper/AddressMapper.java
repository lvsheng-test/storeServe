package org.pack.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pack.store.entity.AddressEntity;
import org.pack.store.requestVo.AddressReq;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AddressMapper {


    AddressEntity getById(@Param("id") String id);
    /**
     * 查询的收货地址
     * @param userId
     * @return
     */
    List<AddressEntity> queryMyAddress(String userId);

    /**
     * 编辑我的收货地址
     * @param addressReq
     */
    void updateMyAddress(AddressReq addressReq);

    /**
     * 添加我的收货地址
     * @param addressEntity
     */
    void insertMyAddress(AddressEntity addressEntity);
}
