package com.hq.CloudPlatform.CA.mapper;

import java.util.List;
import java.util.Map;

import com.hq.CloudPlatform.CA.entity.CaUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hq.CloudPlatform.CA.entity.Role;

/**
 * Created by Administrator on 2017/4/27.
 */
@Repository
public interface CaRoleMapper extends BaseMapper<Role> {
    //查找符合要求的组织和其权限
	List<Role> findAllJoinOrg();
    //根据条件查找所有符合要求的角色
    List<Role> findPageRoleByUserId(Map<String, Object> map);

    void saveUserRole(CaUserRole cur);

    void deleteUserRoleByUserId(String userId);

    void deleteUserRoleByRoleId(String roleId);

    void deleteUserRole(@Param("userId") String userId, @Param("roleId") String roleId);

    Integer getCountByUserId(Map<String, Object> map);
}
