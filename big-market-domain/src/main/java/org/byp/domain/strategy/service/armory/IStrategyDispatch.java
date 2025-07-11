package org.byp.domain.strategy.service.armory;

import org.apache.commons.lang3.ClassUtils;

/**
 * @ClassName IStrategyDispatch
 * @Description 策略抽奖调度
 * @Author 30925
 * @Date 2025/7/8 21:02
 */
// 策略调度
public interface IStrategyDispatch {
    //只用来获取随机奖品ID
    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);//还有可能会有权重的值
}
