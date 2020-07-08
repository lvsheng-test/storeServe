package org.pack.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.pack.store.entity.TransactionDetailEntity;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TransactionDetailMapper {

    /**
     * 生成交易流水明细记录
     * @param transactionDetail
     */
    void insertTransactionDetailInfo(TransactionDetailEntity transactionDetail);
}
