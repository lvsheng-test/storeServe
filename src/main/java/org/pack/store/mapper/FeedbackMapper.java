package org.pack.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.pack.store.entity.FeedbackEntity;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface FeedbackMapper {

    /*
        添加用户意见反馈
     */
    int addFeedback(FeedbackEntity feedbackEntity);

}
