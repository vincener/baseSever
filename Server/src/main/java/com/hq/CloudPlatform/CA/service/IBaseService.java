package com.hq.CloudPlatform.CA.service;


import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.restful.view.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/3/7.
 */
public interface IBaseService<Entity> {

    String generateUUID();

    Page findByPage(Page page) throws ServiceException;

    String save(Entity entity) throws ServiceException;

    boolean update(Entity entity) throws ServiceException;

    boolean deleteById(String id) throws ServiceException;

    boolean batchDelete(List<String> idList) throws ServiceException;

    Entity findById(String id) throws ServiceException;

    Entity findByName(String name) throws ServiceException;

    List<Entity> findAll() throws ServiceException;

    List<Entity> findByMap(Map<String, Object> map) throws ServiceException;

    List<Entity> checkByMap(Map<String, Object> map) throws ServiceException;
}
