package org.pack.store.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/storeTest")
public interface TestService {
    
    /**
     * test
     */
    @PostMapping(value = "/test",produces = "application/json;charset=UTF-8")
    int test(@RequestBody String param);
}
