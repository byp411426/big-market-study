package org.byp.domain.strategy.service.armory;

import lombok.extern.slf4j.Slf4j;
import org.byp.domain.strategy.model.entity.StrategyAwardEntity;
import org.byp.domain.strategy.model.entity.StrategyEntity;
import org.byp.domain.strategy.model.entity.StrategyRuleEntity;
import org.byp.domain.strategy.repository.IStrategyRepository;
import org.byp.types.enums.ResponseCode;
import org.byp.types.exception.AppException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

/**
 * @ClassName StrategyArmoryDispatch
 * @Description 实现接口
 * @Author 30925
 * @Date 2025/7/4 16:59
 */
@Slf4j
@Service("strategyArmory")
public class StrategyArmoryDispatch implements IStrategyArmory, IStrategyDispatch{
    @Resource
    private IStrategyRepository repository;//负责获取数据
    @Override
    public boolean assembleLotteryStrategy(Long strategyId) {
        //1,查相关策略信息(相关策略配置)
        List<StrategyAwardEntity> strategyAwardEntityList = repository.queryStrategyAwardList(strategyId);
        assembleLotteryStrategy(String.valueOf(strategyId), strategyAwardEntityList);

        //2 权重策略配置 - 适用于rule_weight 权重规则配置
        StrategyEntity strategyEntity = repository.queryStrategyEntityByStrategyId(strategyId);//需要查询策略表的信息，他的规则模式底下有哪些东西

        String ruleWeight = strategyEntity.getRuleWeight();
        if(null == ruleWeight) return true;
        //根据策略ID和权重（rule_weight）去查具体的rule_value(这条配置在strategy_rule表中)
        StrategyRuleEntity strategyRuleEntity = repository.queryStrategyRule(strategyId, ruleWeight);

        if (strategyRuleEntity == null)
            throw new AppException(ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getCode(),ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getCode());

        Map<String,List< Integer>> ruleWeightValueMap = strategyRuleEntity.getRuleWeightValue();
        Set<String> keySet = ruleWeightValueMap.keySet();//获取所有的键
        for (String key : keySet) {//所有的键去遍历获取相应的值，10001 10002...
            log.info("key:{}",key);
            //有一个问题就是这个key到底是抽奖次数还是Map集合
            List<Integer> ruleWeightValues = ruleWeightValueMap.get(key);
            ArrayList<StrategyAwardEntity> strategyAwardEntities = new ArrayList<>(strategyAwardEntityList);
            //过滤掉不符合的奖品,奖品ID必须要在权重值里面
            strategyAwardEntities.removeIf(entity -> !ruleWeightValues.contains(entity.getAwardId()));
            //装配抽奖为60次时的奖品，200次的奖品，还有10000次的奖品
            assembleLotteryStrategy(String.valueOf(strategyId).concat("_").concat( key),strategyAwardEntities);

        }

        return true;




    }

    private void assembleLotteryStrategy(String key, List<StrategyAwardEntity> strategyAwardEntityList) {

        //1,获取最小概率值
        BigDecimal minAwardRate = strategyAwardEntityList.stream()//流操作
                .map(StrategyAwardEntity::getAwardRate)//将对象映射为奖品概率
                .min(BigDecimal::compareTo)//获取最小值
                .orElse(BigDecimal.ZERO);//集合为空就返回0
        //2,获取概率值的总和
        BigDecimal totalAwardRate = strategyAwardEntityList.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);//累加器
        //3,用总和值(一般来说是1)除以最小值，得到概率范围
        BigDecimal rateRange = totalAwardRate.divide(minAwardRate, 0, RoundingMode.CEILING);//总和除以最小值，精度为0，向上取整
        //4,生成查找表
        List<Integer> strategyAwardSearchRateTables = new ArrayList<>(rateRange.intValue());//创建一个集合，用于存储概率值
        for (StrategyAwardEntity strategyAward : strategyAwardEntityList) {
            Integer awardId = strategyAward.getAwardId();//获取奖品id，把奖品ID当做是集合中的元素，比如这个ID对应5%，就在100个里面放5个这样的ID
            BigDecimal awardRate = strategyAward.getAwardRate();//获取奖品概率
            //计算每个概率值需要存放到查找表的数量
            for (int i = 0; i < rateRange.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue(); i++) {
                strategyAwardSearchRateTables.add(awardId);//你只要加进去这么多个就行，相当于8%概率，1000个格子，只要在80个格子中加上ID值就行，不需要有顺序

            }

        }
        //5存储乱序
        Collections.shuffle(strategyAwardSearchRateTables);
        //原先是List集合存储，改成HashMap，需要存储下标和对应的List集合中的值
        //6生成集合，把 List 的下标 → 奖品 ID 做成一个键值映射，hashmap更适合存储到Redis中，Redis 通常不适合直接存 Java 的 List 对象
        HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTables = new HashMap<>();
        for (int i = 0; i < strategyAwardSearchRateTables.size(); i++) {
            shuffleStrategyAwardSearchRateTables.put(i, strategyAwardSearchRateTables.get(i));
        }

        //7 存储到Redis，因为是分布式的，所以需要存储到Redis中
        repository.storeStrategyAwardSearchRateTable(key,shuffleStrategyAwardSearchRateTables.size(),shuffleStrategyAwardSearchRateTables);


    }

    @Override//抽奖方法，给一个策略ID，还有随机数所在的范围，就能实现随机抽奖
    public Integer getRandomAwardId(Long strategyId) {
        int rateRange = repository.getRateRange(strategyId);
        //通过生成的随机值去查找表，获取奖品ID
        return repository.getStrategyAwardAssemble(String.valueOf(strategyId), new SecureRandom().nextInt(rateRange));
    }

    @Override
    public Integer getRandomAwardId(Long strategyId, String ruleWeightValue) {
        String key = String.valueOf(strategyId).concat("_").concat(ruleWeightValue);
        int rateRange = repository.getRateRange(key);
        return repository.getStrategyAwardAssemble(key, new SecureRandom().nextInt(rateRange));

    }
}
