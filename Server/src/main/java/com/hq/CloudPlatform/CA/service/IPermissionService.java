package com.hq.CloudPlatform.CA.service;


import java.util.List;
import java.util.Set;

import com.hq.CloudPlatform.CA.entity.CaRolePermission;
import com.hq.CloudPlatform.CA.entity.CaUserRolePermission;
import com.hq.CloudPlatform.CA.entity.Permission;
import com.hq.CloudPlatform.CA.exception.ServiceException;

/**
 * Created by admin on 2017/3/7.
 */
public interface IPermissionService extends IBaseService<Permission> {
	//通过登录名查询该用户下的所有权限
    Set<String> getPermissionStringsByLoginName(String loginName) throws ServiceException;
    
    List<Permission> findByRoleId(String id);
    //通过登录用户的id查询相关的权限
    List<CaUserRolePermission>  findByUserRolePer(String userId, String appCode) throws ServiceException;

    void saveRolePermission(CaRolePermission crp)throws ServiceException;
    
    void deleteRolePermission(String roleId)throws ServiceException;
}
