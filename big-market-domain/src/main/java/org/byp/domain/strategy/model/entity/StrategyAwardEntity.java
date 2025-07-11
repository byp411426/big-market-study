package org.byp.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName StrategyAwardEntity
 * @Description 策略奖品实体
 * @Author 30925
 * @Date 2025/7/4 17:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardEntity {
    /** 抽奖策略ID*/
    private String strategyId;
    /** 抽奖奖品ID - 内部流转使用*/
    private Integer awardId;
    /** 奖品库存总量*/
    private Integer awardCount;
    /** 奖品库存剩余*/
    private Integer awardCountSurplus;
    /** 奖品中奖概率*/
    private BigDecimal awardRate;
}
