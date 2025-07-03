package org.byp.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.byp.infrastructure.persistent.po.StrategyRule;

import java.util.List;

/**
 * @ClassName IStrategyRuleDao
 * @Description 策略规则Dao
 * @Author 30925
 * @Date 2025/7/2 21:19
 */
@Mapper
public interface IStrategyRuleDao {
    List<StrategyRule> queryStrategyRuleList();
}
