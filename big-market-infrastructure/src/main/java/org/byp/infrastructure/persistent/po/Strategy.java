package org.byp.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Strategy
 * @Description 抽奖策略
 * @Author 30925
 * @Date 2025/7/2 14:36
 */
@Data
public class Strategy {
    /** 自增ID*/
    private Long id;
    /** 抽奖策略ID*/
    private Long strategyId;
    /** 抽奖策略描述*/
    private String strategyDesc;
    /** 规则模型，rule配置的模型同步到此表，便于使用*/
    private String ruleModels;
    /** 创建时间*/
    private Date createTime;
    /** 更新时间*/
    private Date updateTime;
}
