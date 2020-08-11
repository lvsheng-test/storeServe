package org.pack.store.api;

import com.alibaba.fastjson.JSONObject;
import org.pack.store.requestVo.AppVO;
import org.pack.store.utils.AppletResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserService {
    
    /**
     * login
     */
    @PostMapping(value = "/login",produces = "application/json;charset=UTF-8")
    AppletResult login(@RequestBody AppVO<JSONObject> jsonObject);
}
