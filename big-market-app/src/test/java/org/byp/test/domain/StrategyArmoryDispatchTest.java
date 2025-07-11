package org.byp.test.domain;

import lombok.extern.slf4j.Slf4j;
import org.byp.domain.strategy.service.armory.IStrategyArmory;
import org.byp.domain.strategy.service.armory.IStrategyDispatch;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName StrategyArmoryDispatchTest
 * @Description 测试策略装配功能
 * @Author 30925
 * @Date 2025/7/7 19:48
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyArmoryDispatchTest {
    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private IStrategyDispatch strategyDispatch;

    @Before
    public void test_strategyArmory() {
        boolean result = strategyArmory.assembleLotteryStrategy(100001L);
        log.info("测试结果:{}",result);
    }
    @Test
    public void test_getRandomAwardId() {
        for (int i = 0; i < 100; i++) {
            log.info("测试结果{} - 奖品ID值:{}",i,strategyDispatch.getRandomAwardId(100001L));

        }
    }

    @Test
    public void test_getRandomAwardId_ruleWeightValue() {
        //60:102,103,104,105 200:106,107 1000:105
        for (int i = 0; i < 100; i++) {
            log.info("测试结果60 - 奖品ID值:{}",strategyDispatch.getRandomAwardId(100001L,"60:102,103,104,105"));}
        for (int i = 0; i < 100; i++) {
            log.info("测试结果200 - 奖品ID值:{}",strategyDispatch.getRandomAwardId(100001L,"200:106,107"));
        }



    }

}
