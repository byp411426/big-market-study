package org.byp.domain.strategy.service.armory;

import org.springframework.stereotype.Service;

/**
 * @ClassName IStrategyArmory
 * @Description 策略装配库（兵工厂），负责初始化策略计算
 * @Author 30925
 * @Date 2025/7/4 16:01
 */
@Service("strategyArmory")
public interface IStrategyArmory {

    //只用来装配
    boolean assembleLotteryStrategy(Long strategyId);



}
