package org.pack.store.api;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.utils.AppletResult;

public interface GoodsCategoryService {

    /**
     * 查询店内的菜品分类
     */
    AppletResult queryCategoryList(JSONObject jsonObject);
}
