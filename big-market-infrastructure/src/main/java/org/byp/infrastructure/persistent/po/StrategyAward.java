package org.byp.infrastructure.persistent.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName StrategyAward
 * @Description 策略奖品，明细配置，含有概率和规则
 * @Author 30925
 * @Date 2025/7/2 17:02
 */
@Data
public class StrategyAward {

    /** 自增ID*/
    private Long id;
    /** 抽奖策略ID*/
    private String strategyId;
    /** 抽奖奖品ID - 内部流转使用*/
    private Integer awardId;
    /** 抽奖奖品标题*/
    private String awardTitle;
    /** 抽奖奖品副标题*/
    private String awardSubtitle;
    /** 奖品库存总量*/
    private Integer awardCount;
     /** 奖品库存剩余*/
    private Integer awardCountSurplus;
      /** 奖品中奖概率*/
    private BigDecimal awardRate;
    /** 规则模型，rule配置的模型同步到此表，便于使用*/
    private String ruleModels;
     /** 排序*/
    private Integer sort;
    /** 创建时间*/
    private Date createTime;
    /** 更新时间*/
    private Date updateTime;
}
