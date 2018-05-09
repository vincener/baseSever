package com.hq.CloudPlatform.CA.service;

import java.util.List;

import com.hq.CloudPlatform.CA.entity.CaUserRole;
import com.hq.CloudPlatform.CA.entity.Role;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.restful.view.Page;

/**
 * Created by Administrator on 2017/4/27.
 */
public interface IRoleService extends IBaseService<Role> {
	//查询角色下的机构和角色信息
	List<Role> findAllJoinOrg();

	Page findPageRoleByUserId(Page page) throws ServiceException;

	void deleteUserRoleByUserId(String userId)throws ServiceException;

	void deleteUserRoleByRoleId(String roleId)throws ServiceException;

	void saveUserRole(CaUserRole cur)throws ServiceException;

	boolean deleteUserRole(String userId,String roleId)throws ServiceException;

}
