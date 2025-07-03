package org.byp.test.infrastructure;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.byp.infrastructure.persistent.dao.IStrategyAwardDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName StrategyAwardTest
 * @Description TODO
 * @Author 30925
 * @Date 2025/7/3 13:54
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyAwardTest {
    @Resource
    private IStrategyAwardDao strategyAwardDao;
    @Test
    public void test_queryStrategyAwardDao() {
        log.info("queryStrategyAwardDao结果为: {}", JSON.toJSONString(strategyAwardDao.queryStrategyAwardList()));
    }
}
