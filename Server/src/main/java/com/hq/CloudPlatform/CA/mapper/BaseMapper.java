package com.hq.CloudPlatform.CA.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/3/7.
 */
public interface BaseMapper<Entity> {

    void save(Entity entity);

    void update(Entity entity);

    void deleteById(String id);
    
    void deleteByIdByLogic(String id);

    void batchDelete(List<String> idList);

    Entity findById(String id);

    Entity findByName(String name);

    List<Entity> findAll();

    List<Entity> findByMap(Map<String, Object> map);

    Integer getCount(Map<String, Object> map);

    List<Entity> findByPage(Map<String, Object> map);
    //根据不同条件验证字段是否存在
    List<Entity> checkByMap(Map<String, Object> map);
}
