package org.byp.test;

import lombok.extern.slf4j.Slf4j;

import org.byp.infrastructure.persistent.redis.IRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private IRedisService redisService;
    @Test
    public void test() {

        Map<Object,Object> map = redisService.getMap("info");
        map.put("name","张三");
        map.put("age",18);
        map.put("sex","男");

        log.info("测试完成:{}",redisService.getFromMap("info","name").toString());

    }

}
