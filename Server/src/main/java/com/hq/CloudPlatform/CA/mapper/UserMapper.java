package com.hq.CloudPlatform.CA.mapper;

import com.hq.CloudPlatform.CA.entity.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/3/7.
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    //查找所有的用户
    List<User> findAllUser();

    //根据登陆账号查询用户信息
    User findByLongName(String login_name);

    // 删除机构时，查看该机构下是否有成员
    int findExistUserByOrgId(String orgId);

    // 验证修改密码时，密码输入是否正确
    int checkPassword(@Param("loginName") String loginName, @Param("password") String password);

    //根据当前机构id只查询本机构，关联的人员信息
    List<User> findUserByCurrentOrgId(Map<String,Object> map);

    Integer findUserByCurrentOrgIdCount(Map<String, Object> map);

    //根据当前机构id查询该机构所有子机构，关联的人员信息
    List<User> findUserByOrgId(Map<String, Object> map);

    Integer findUserByOrgIdCount(Map<String, Object> map);

    List<User> findPageRoleById(Map<String, Object> map);

    Integer getCountWithRoleId(Map<String, Object> map);

    List<User> findByPageWithRoleId(Map<String, Object> map);

    Integer getCountOrgManager(Map<String, Object> map);

    List<User> findPageOrgManager(Map<String, Object> map);

    List<User> findAllWithRoleId(Map<String, Object> map);
    //批量禁用
    void batchDisable(@Param("idList") List<String> idList, @Param("loceDate")Date loceDate);
    //批量启用
    void batchEnable(@Param("idList") List<String> idList, @Param("loceDate")Date loceDate);

    List<User> findAllOrgManagerByOrgId(Map<String, Object> map);

}
