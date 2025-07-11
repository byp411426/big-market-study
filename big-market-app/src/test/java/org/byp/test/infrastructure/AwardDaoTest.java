package org.byp.test.infrastructure;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.byp.infrastructure.persistent.dao.IAwardDao;
import org.byp.infrastructure.persistent.dao.IStrategyAwardDao;
import org.byp.infrastructure.persistent.po.Award;
import org.byp.infrastructure.persistent.po.StrategyAward;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName AwardDaoTest
 * @Description 奖品持久化单元测试
 * @Author 30925
 * @Date 2025/7/2 21:58
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AwardDaoTest {

    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyAwardDao strategyAwardDao;
    @Test
    public void test_queryAwardDao(){
//        log.info("测试奖品持久化");
//        List<Award> awards = awardDao.queryAwardList();
//        log.info( "测试奖品持久化结果：{}", JSON.toJSONString(awards));
        List<StrategyAward> res = strategyAwardDao.queryStrategyAwardListByStrategyId(100002L);
        log.info("测试策略奖品持久化结果：{}", JSON.toJSONString(res));
    }
}
