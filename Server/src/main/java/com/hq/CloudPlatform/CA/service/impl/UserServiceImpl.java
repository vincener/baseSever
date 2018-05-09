package com.hq.CloudPlatform.CA.service.impl;

import com.hq.CloudPlatform.CA.entity.Role;
import com.hq.CloudPlatform.CA.entity.User;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.mapper.BaseMapper;
import com.hq.CloudPlatform.CA.mapper.UserMapper;
import com.hq.CloudPlatform.CA.restful.view.Page;
import com.hq.CloudPlatform.CA.service.IUserService;
import com.hq.CloudPlatform.CA.utils.IDGenerator;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by admin on 2017/3/7.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public BaseMapper<User> getBaseMapper() {
        return userMapper;
    }


    public User findByLoginName(String loginName) throws ServiceException {
        try {

            User userList = userMapper.findByLongName(loginName);
            return userList;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    //通过不同条件查询用户信息，如登录名
    public Set<String> getRoleStringsByUserName(String username) throws ServiceException {
        try {
            Map<String, Object> params = new HashedMap();
            params.put("loginName", username);
            List<User> userList = userMapper.findByMap(params);

            if (userList != null && userList.size() > 0) {
                // 由于登陆名不允许重名，返回的列表只会有一个，所以直接取第一个
                List<Role> roleList = userList.get(0).getRoleList();
                Set<String> roleNameList = new HashSet<String>();

                for (Role role : roleList) {
                    roleNameList.add(role.getCode());
                }

                return roleNameList;
            } else {
                throw new ServiceException("用户名不存在！");
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    /**
     * 查询所有用户不关联角色信息
     */
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    /*@RequiresPermissions(value={"user:view"})*/
    public List<User> findAll() throws ServiceException {
        return super.findAll();
    }


    //逻辑删除用户信息，删除成功返回true，失败返回false
    public Map<String, Object> deleteByIdByLogic(String id) {
        Map<String, Object> result = new HashedMap();
        try {
            userMapper.deleteByIdByLogic(id);
            result.put("message", "true");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.put("message", "false");
        }
        return result;
    }


    //根据登陆账号查询用户信息
    public Map<String, String> findByLongName(String login_name) throws ServiceException {
        User user = userMapper.findByLongName(login_name);
        Map<String, String> result = new HashedMap();
        if (user != null)
            result.put("message", "false");
        else
            result.put("message", "true");
        return result;
    }

    //修改用户信息
    @Override
    public boolean update(User entity) throws ServiceException {
        try {
            if (entity.getId() == null) {
                return false;
            }
            User user = this.getBaseMapper().findById(entity.getDirectManagerId());
            if(user != null) {
                entity.setDirectManagerName(user.getUsername());
            }
            this.getBaseMapper().update(entity);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    //根据当前机构id查询该机构所有子机构，关联的人员信息
    @Override
    public Page findUserByOrgId(Page page) throws ServiceException {
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

            page.setTotal(userMapper.findUserByOrgIdCount(queryParams));
            queryParams.put("startRowNum", page.getStartRowNum());
            queryParams.put("endRowNum", page.getEndRowNum());
            queryParams.put("pageSize", page.getPageSize());
            queryParams.put("orderBy", page.getOrderBy());
            queryParams.put("orderFields", page.getOrderFields());
            page.setRows(userMapper.findUserByOrgId(queryParams));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return page;
    }

    @Override
    public int findExistUserByOrgId(String orgId) throws ServiceException {
        int count;
        try {
            count = userMapper.findExistUserByOrgId(orgId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return count;
    }

    @Override
    public int checkPassword(String loginName, String password) throws ServiceException {
        return userMapper.checkPassword(loginName, password);
    }

    @Override
    public Page findUserByCurrentOrgId(Page page) throws ServiceException {
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

            page.setTotal(userMapper.findUserByCurrentOrgIdCount(queryParams));
            queryParams.put("startRowNum", page.getStartRowNum());
            queryParams.put("endRowNum", page.getEndRowNum());
            queryParams.put("pageSize", page.getPageSize());
            queryParams.put("orderBy", page.getOrderBy());
            queryParams.put("orderFields", page.getOrderFields());
            page.setRows(userMapper.findUserByCurrentOrgId(queryParams));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return page;
    }

    @Override
    public Page findByPageWithRoleId(Page page) throws ServiceException {
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

            page.setTotal(userMapper.getCountWithRoleId(queryParams));
            queryParams.put("startRowNum", page.getStartRowNum());
            queryParams.put("endRowNum", page.getEndRowNum());
            queryParams.put("pageSize", page.getPageSize());
            queryParams.put("orderBy", page.getOrderBy());
            queryParams.put("orderFields", page.getOrderFields());
            page.setRows(userMapper.findByPageWithRoleId(queryParams));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return page;
    }

    @Override
    public List<User> findAllWithRoleId(Map<String, Object> map) throws ServiceException {
        List<User> list;

        try {
            list = userMapper.findAllWithRoleId(map);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return list;
    }

	@Override
	public Page findPageRoleById(Page page) throws ServiceException {
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
			page.setRows(userMapper.findPageRoleById(queryParams));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}

		return page;
	}

    @Override
    public String save(User user) throws ServiceException {
        String id = IDGenerator.getID();

        try {
            if (StringUtils.isBlank(user.getId()) || user.getId().equalsIgnoreCase("0")) {
                user.setId(id);
            }
            user.setCreateDate(new Date());
            User uu = this.getBaseMapper().findById(user.getDirectManagerId());
            user.setDirectManagerName(uu.getUsername());
            this.getBaseMapper().save(user);

        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return id;
    }

    @Override
    public Page findPageOrgManager(Page page) throws ServiceException {
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

            page.setTotal(userMapper.getCountOrgManager(queryParams));
            queryParams.put("startRowNum", page.getStartRowNum());
            queryParams.put("endRowNum", page.getEndRowNum());
            queryParams.put("pageSize", page.getPageSize());
            queryParams.put("orderBy", page.getOrderBy());
            queryParams.put("orderFields", page.getOrderFields());
            page.setRows(userMapper.findPageOrgManager(queryParams));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return page;
    }

    @Override
    public boolean batchDisable(List<String> idList,Date loceDate) throws ServiceException {
        try {
            userMapper.batchDisable(idList,loceDate);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return true;
    }

    @Override
    public boolean batchEnable(List<String> idList,Date loceDate) throws ServiceException {
        try {
            userMapper.batchEnable(idList,loceDate);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return true;
    }

    @Override
    public List<User> findAllOrgManagerByOrgId(Map<String, Object> map) throws ServiceException {
        List<User> list;

        try {
            list = userMapper.findAllOrgManagerByOrgId(map);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return list;
    }
}
