package org.byp.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.byp.domain.strategy.model.entity.StrategyEntity;
import org.byp.infrastructure.persistent.po.Strategy;
import org.byp.infrastructure.persistent.po.StrategyRule;

import java.util.List;

/**
 * @ClassName IStrategyDao
 * @Description 抽奖策略DAO
 * @Author 30925
 * @Date 2025/7/2 21:18
 */
@Mapper
public interface IStrategyDao {
    List<Strategy> queryStrategyList();

    Strategy queryStrategyByStrategyId(Long strategyId);

    StrategyRule queryStrategyRule(StrategyRule strategyRuleReq);
}
