package org.pack.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.pack.store.entity.AliyunOssEntity;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AliyunOssMapper {
    /**
     * 获取OOS信息
     * @return
     */
    AliyunOssEntity getAliyunOssInfo();
}
