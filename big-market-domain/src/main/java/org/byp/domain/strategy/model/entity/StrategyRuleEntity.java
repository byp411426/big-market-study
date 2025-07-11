package org.byp.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.byp.types.common.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StrategyRuleEntity
 * @Description 策略规则的实体
 * @Author 30925
 * @Date 2025/7/8 22:32
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyRuleEntity {
    /** 抽奖策略ID*/
    private Long strategyId;
    /** 抽奖奖品ID【规则类型为策略，则不需要奖品ID】*/
    private Integer awardId;
    /** 抽象规则类型；1-策略规则、2-奖品规则*/
    private Integer ruleType;
    /** 抽奖规则类型【rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)】*/
    private String ruleModel;
    /** 抽奖规则比值*/
    private String ruleValue;
    /** 抽奖规则描述*/
    private String ruleDesc;

    //定义从rule_value字段中获取具体的内容
    public Map<String, List<Integer>> getRuleWeightValue() {
        //要处理的数据：60:102,103,104,105 200:106,107 1000:105
        //判断一下是不是rule_weight规则，是再处理
        if (!"rule_weight".equals(ruleModel)) return null;
        //用空格分隔去进行第一步处理，得到60:102,103,104,105和200:106,107 1000:105
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<String, List<Integer>> resultMap = new HashMap<>();
        for (String ruleValueGroup : ruleValueGroups) {
            //检查输入是否为空
            if (ruleValueGroup == null || ruleValueGroup.isEmpty()){
                return resultMap;
            }
            //分割字符串以获取键和值，根据冒号进行分割60:102,103,104,105
            String[] parts = ruleValueGroup.split(Constants.COLON);
            if (parts.length != 2){//分割的结果肯定是2个
                //返回规则权重值非法格式
                throw new IllegalArgumentException("rule_weight rule_rule invalid input format"+ ruleValueGroup);
            }
            //解析值，再对值进行逗号分隔，得到102,103,104,105的数据
            String[] valueStrings = parts[1].split(Constants.SPLIT);
            List<Integer> values = new ArrayList<>();
            for (String valueString : valueStrings) {
                    values.add(Integer.parseInt(valueString));//转换为整数
            }
            //将键和值放入Map中
            resultMap.put(ruleValueGroup, values);//键是60:102,103,104,105，值是102,103,104,105
        }
        return resultMap;
    }
}
