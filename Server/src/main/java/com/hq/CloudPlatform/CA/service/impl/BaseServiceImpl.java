package com.hq.CloudPlatform.CA.service.impl;

import com.hq.CloudPlatform.CA.entity.BaseEntity;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.mapper.BaseMapper;
import com.hq.CloudPlatform.CA.restful.view.Page;
import com.hq.CloudPlatform.CA.service.IBaseService;
import com.hq.CloudPlatform.CA.utils.IDGenerator;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/3/7.
 */
public abstract class BaseServiceImpl<Entity extends BaseEntity> implements IBaseService<Entity> {

    public abstract BaseMapper<Entity> getBaseMapper();

    @Override
    public String generateUUID() {
        return IDGenerator.getID();
    }

    /**
     * 分页查询
     */
    @Override
    public Page findByPage(Page page) throws ServiceException {
        if (page.getStartRowNum() >= page.getEndRowNum()) {
            throw new ServiceException("分页查询时开始行必须小于结束行");
        }

        //单页查询不允许超过500条，防止恶意调用page size过大导致内存溢出
        if (page.getPageSize() > Page.MAX_PAGE_SIZE) {
            throw new ServiceException("超过允许查询的单页记录最大值");
        }

        try {
            Map<String, Object> queryParams = new HashMap<String, Object>();
            Map<String, Object> conditions = page.getConditions();

            if (null != conditions) {
                queryParams.putAll(conditions);
            }

            page.setTotal(this.getBaseMapper().getCount(queryParams));
            queryParams.put("startRowNum", page.getStartRowNum());
            queryParams.put("endRowNum", page.getEndRowNum());
            queryParams.put("pageSize", page.getPageSize());
            queryParams.put("orderBy", page.getOrderBy());
            queryParams.put("orderFields", page.getOrderFields());
            page.setRows(this.getBaseMapper().findByPage(queryParams));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return page;
    }

    /**
     * 实现新增
     */
    @Override
    public String save(Entity entity) throws ServiceException {

        try {
            if (StringUtils.isBlank(entity.getId()) || entity.getId().equalsIgnoreCase("0")) {
                String id = IDGenerator.getID();
                entity.setId(id);
            }
            entity.setCreateDate(new Date());
            this.getBaseMapper().save(entity);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return entity.getId();
    }

    /**
     * 修改信息
     */
    @Override
    public boolean update(Entity entity) throws ServiceException {
        try {
            entity.setUpdateDate(new Date());
            if (entity.getId() == null) {
                return false;
            }
            this.getBaseMapper().update(entity);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return true;
    }

    /**
     * 通过id删除
     */
    @Override
    public boolean deleteById(String id) throws ServiceException {
        try {
            this.getBaseMapper().deleteById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return true;
    }

    /**
     * 批量删除
     */
    @Override
    public boolean batchDelete(List<String> idList) throws ServiceException {
        try {
            this.getBaseMapper().batchDelete(idList);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return true;
    }

    /**
     * 通过id查询
     */
    @Override
    public Entity findById(String id) throws ServiceException {
        Entity entity;

        try {
            entity = this.getBaseMapper().findById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return entity;
    }

    @Override
    public Entity findByName(String name) throws ServiceException {
        Entity entity;

        try {
            entity = this.getBaseMapper().findByName(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return entity;
    }

    /**
     * 查询所有信息
     */
    @Override
    public List<Entity> findAll() throws ServiceException {
        List<Entity> list;

        try {
            list = this.getBaseMapper().findAll();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return list;
    }

    /**
     * 根据不同的条件查询
     */
    @Override
    public List<Entity> findByMap(Map<String, Object> map) throws ServiceException {
        List<Entity> list;

        try {
            list = this.getBaseMapper().findByMap(map);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return list;
    }

    /**
     * 在编辑时对字段进行验证，根据不同的条件查询
     */
    @Override
    public List<Entity> checkByMap(Map<String, Object> map) throws ServiceException {
        List<Entity> list;

        try {
            list = this.getBaseMapper().checkByMap(map);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return list;
    }

}
