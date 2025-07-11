package org.byp.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.byp.types.common.Constants;

/**
 * @ClassName StrategyEntity
 * @Description 策略实体对象
 * @Author 30925
 * @Date 2025/7/8 21:38
 */
//策略实体对象
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
public class StrategyEntity {
    /** 抽奖策略ID*/
    private Long strategyId;
    /** 抽奖策略描述*/
    private String strategyDesc;
    /** 规则模型，rule配置的模型同步到此表，便于使用*/
    private String ruleModels;//这玩意是字符串，需要将重要的东西拆出来

    public String[] ruleModels(){//获取规则模型
        if(StringUtils.isBlank(ruleModels)) return null;
        return ruleModels.split(Constants.SPLIT);
    }
    public String getRuleWeight() {//看看规则模型中有哪些内容
       String[] ruleModels = this.ruleModels();
        for (String ruleModel : ruleModels) {
            if("rule_weight".equals(ruleModel)) return ruleModel;
        }
        return null;
    }

}
