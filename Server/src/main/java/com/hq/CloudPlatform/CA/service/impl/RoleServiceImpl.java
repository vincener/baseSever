package com.hq.CloudPlatform.CA.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.CloudPlatform.CA.entity.CaUserRole;
import com.hq.CloudPlatform.CA.restful.view.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hq.CloudPlatform.CA.entity.Role;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.mapper.BaseMapper;
import com.hq.CloudPlatform.CA.mapper.CaRoleMapper;
import com.hq.CloudPlatform.CA.service.IRoleService;

/**
 * Created by Administrator on 2017/4/27.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

    @Autowired
    private CaRoleMapper caRoleMapper;

    public BaseMapper<Role> getBaseMapper(){
        return caRoleMapper;

    }
    //查询所有角色信息
    @Override
    public List<Role> findAll() throws ServiceException {
        return super.findAll();
    }
    //查询该角色下的机构和角色信息
	@Override
	public List<Role> findAllJoinOrg() {
		
		return caRoleMapper.findAllJoinOrg();
	}

    @Override
    public Page findPageRoleByUserId(Page page) throws ServiceException {
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

            page.setTotal(caRoleMapper.getCountByUserId(queryParams));
            queryParams.put("startRowNum", page.getStartRowNum());
            queryParams.put("endRowNum", page.getEndRowNum());
            queryParams.put("pageSize", page.getPageSize());
            queryParams.put("orderBy", page.getOrderBy());
            queryParams.put("orderFields", page.getOrderFields());
            page.setRows(caRoleMapper.findPageRoleByUserId(queryParams));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return page;
    }

    @Override
    public void deleteUserRoleByUserId(String userId) throws ServiceException {
        caRoleMapper.deleteUserRoleByUserId(userId);
    }

    @Override
    public void deleteUserRoleByRoleId(String roleId) throws ServiceException {
        caRoleMapper.deleteUserRoleByRoleId(roleId);
    }

    @Override
    public void saveUserRole(CaUserRole cur) throws ServiceException {
        cur.setCreateDate(new Date());
        caRoleMapper.saveUserRole(cur);
    }

    @Override
    public boolean deleteUserRole(String userId, String roleId) throws ServiceException {
        try {
            caRoleMapper.deleteUserRole(userId,roleId);
            } catch (Exception e) {
        throw new ServiceException(e.getMessage(), e);
    }
        return true;
    }
}
