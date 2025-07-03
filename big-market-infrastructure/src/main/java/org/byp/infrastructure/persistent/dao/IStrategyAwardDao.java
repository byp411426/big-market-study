package org.byp.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.byp.infrastructure.persistent.po.StrategyAward;

import java.util.List;

/**
 * @ClassName IStrategyAwardDao
 * @Description 策略奖品，明细配置，含有概率和规则Dao
 * @Author 30925
 * @Date 2025/7/2 21:18
 */
@Mapper
public interface IStrategyAwardDao {
    List<StrategyAward> queryStrategyAwardList();
}
