package com.hq.CloudPlatform.CA.mapper;

import com.hq.CloudPlatform.CA.entity.CaRolePermission;
import com.hq.CloudPlatform.CA.entity.CaUserRolePermission;
import com.hq.CloudPlatform.CA.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2017/3/7.
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    //通过登录名字查找
    List<Permission> findByLoginName(String loginName);

    List<Permission> findByRoleId(String id);

    //通过传递的id值去查找该用户下的所有一级权限目录
    List<CaUserRolePermission> findByUserRolePer(@Param("userId") String userId, @Param("appCode") String appCode);

    void deleteRolePermissionByRoleId(String roleId);

    void saveRolePermission(CaRolePermission crp);

}
