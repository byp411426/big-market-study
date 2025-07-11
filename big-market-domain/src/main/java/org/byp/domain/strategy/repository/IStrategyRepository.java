package org.byp.domain.strategy.repository;

import org.byp.domain.strategy.model.entity.StrategyAwardEntity;
import org.byp.domain.strategy.model.entity.StrategyEntity;
import org.byp.domain.strategy.model.entity.StrategyRuleEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IStrategyRepository
 * @Description 策略的仓储接口
 * @Author 30925
 * @Date 2025/7/4 17:03
 */

public interface IStrategyRepository {
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);



    void storeStrategyAwardSearchRateTable(String key, Integer rateRange, Map<Integer, Integer> strategyAwardSearchRateTable);



    Integer getStrategyAwardAssemble(String key, Integer rateKey);
    int getRateRange(Long strategyId);

    int getRateRange(String key);

    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);
}
