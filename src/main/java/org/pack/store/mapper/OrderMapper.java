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

    int inertTrans(JSONObject jsonObject);

    int updateBalance(JSONObject jsonObject);

    int editOrder(JSONObject jsonObject);

    /**
     * 查询个人所有订单
     * @param openId
     * @return
     */
    List<JSONObject> queryOrderByOpenIdAll(@Param("openId") String openId);

    /**
     * 查询个人待支付状态下的订单列表信息
     * @param openId
     * @return
     */
    List<JSONObject> queryOrderByOpenIdAndPending(@Param("openId") String openId);

    /**
     * 查询个人待收货状态下的订单列表信息
     * @param openId
     * @return
     */
    List<JSONObject> queryOrderByOpenIdAndReceipt(@Param("openId") String openId);

    /**
     * 根据订单号查询订单购买的商品信息
     * @param orderId
     * @return
     */
    List<JSONObject> queryOrderDetail(@Param("orderId") String orderId);

    /**
     * 查询订单详情
     * @param orderId
     * @param openId
     * @return
     */
    JSONObject queryOrderInfo(@Param("orderId") String orderId,@Param("openId") String openId);

    /**
     * 查询该订单的配送员信息
     * @param orderId
     * @return
     */
    JSONObject queryOrderDeliveryInfo(@Param("orderId") String orderId);

    int deleteOrderInfo(@Param("orderId") String orderId,@Param("openId") String openId);

    /**
     * 取消订单
     */
    int cancelOrder(@Param("orderId") String orderId,@Param("openId") String openId,@Param("orderStatus") Integer orderStatus);
}
