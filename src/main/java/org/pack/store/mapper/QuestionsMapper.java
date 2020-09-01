package org.pack.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.pack.store.entity.QuestionsEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionsMapper {
    /**
     * 添加常见问题信息
     * @param QuestionsEntity
     * @return
     */
    int addQuestionsInfo(QuestionsEntity QuestionsEntity);

    List<QuestionsEntity> queryQuestionsList();
}
