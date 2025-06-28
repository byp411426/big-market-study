package org.byp.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.byp.infrastructure.persistent.po.Award;

import java.util.List;

/**
 * @ClassName IAwardDao
 * @Description 奖品表接口
 * @Author 30925
 * @Date 2025/7/2 21:15
 */
@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();
}
