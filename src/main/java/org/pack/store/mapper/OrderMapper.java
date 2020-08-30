package org.pack.store.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.pack.store.entity.AddressEntity;
import org.pack.store.requestVo.AddressReq;
import org.pack.store.requestVo.DelAddressReq;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface OrderMapper {

    JSONObject getByOrderId(@Param("orderId") String orderId,@Param("openId") String openId);

    int placeOrder(JSONObject jsonObject);

    int insertDetail(List<JSONObject> list);

    List<JSONObject> goodsDetail(String orderId);
}
