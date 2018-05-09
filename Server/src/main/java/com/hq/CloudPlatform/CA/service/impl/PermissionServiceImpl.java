package com.hq.CloudPlatform.CA.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hq.CloudPlatform.CA.entity.CaRolePermission;
import com.hq.CloudPlatform.CA.entity.CaUserRolePermission;
import com.hq.CloudPlatform.CA.entity.Permission;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.mapper.BaseMapper;
import com.hq.CloudPlatform.CA.mapper.PermissionMapper;
import com.hq.CloudPlatform.CA.service.IPermissionService;

/**
 * Created by admin on 2017/3/7.
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public BaseMapper<Permission> getBaseMapper() {
        return permissionMapper;
    }
    //通过登录名查询该用户下的所有权限
    public Set<String> getPermissionStringsByLoginName(String loginName) throws ServiceException {
        List<Permission> permissionList = permissionMapper.findByLoginName(loginName);
        Set<String> permissionStrings = new HashSet<String>();

        for (Permission permission : permissionList) {
            permissionStrings.add(permission.getCode());
        }

        return permissionStrings;
    }
    //通过登录用户的id值查询其下的所有权限
	@Override
	public List<CaUserRolePermission> findByUserRolePer(String userId, String appCode) throws ServiceException {
		return permissionMapper.findByUserRolePer(userId, appCode);
	}

	@Override
	public void saveRolePermission(CaRolePermission crp) throws ServiceException {
		crp.setCreateDate(new Date());
		permissionMapper.saveRolePermission(crp);
	}

	@Override
	public void deleteRolePermission(String roleId) throws ServiceException {
		permissionMapper.deleteRolePermissionByRoleId(roleId);
	}
	/* (non-Javadoc)
	 * @see com.hq.CloudPlatform.CA.service.IPermissionService#findByRoleId(java.lang.String)
	 */
	@Override
	public List<Permission> findByRoleId(String id) {
		
		return permissionMapper.findByRoleId(id);
	}
}
