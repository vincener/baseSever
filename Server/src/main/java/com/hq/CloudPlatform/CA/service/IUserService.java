package com.hq.CloudPlatform.CA.service;


import com.hq.CloudPlatform.CA.entity.User;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.restful.view.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2017/3/7.
 */
public interface IUserService extends IBaseService<User> {
    //查询所有用户信息
    List<User> findAllUser() throws ServiceException;
    //通过登录名查询用户信息
    User findByLoginName(String name) throws ServiceException;
    //通过登录名查询用户信息
    Set<String> getRoleStringsByUserName(String username) throws ServiceException;
    
    //逻辑删除用户信息，删除成功返回true，失败返回false
    Map<String,Object> deleteByIdByLogic(String id) throws ServiceException;
    
    //根据登陆账号查询用户信息
    Map<String,String> findByLongName(String login_name) throws ServiceException;

    //根据当前机构id只查询本机构，关联的人员信息
    Page findUserByCurrentOrgId(Page page) throws ServiceException;

    //根据当前机构id查询该机构所有子机构，关联的人员信息
	Page findUserByOrgId(Page page) throws ServiceException;

	// 删除机构时，查看该机构下是否还有成员
    int findExistUserByOrgId(String orgId) throws ServiceException;

    // 验证修改密码时，密码输入是否正确
    int checkPassword(String loginName, String password) throws ServiceException;

    Page findPageRoleById(Page page) throws ServiceException;

    Page findByPageWithRoleId(Page page) throws ServiceException;
    //查找某个机构下的机构管理员
    Page findPageOrgManager(Page page) throws ServiceException;

    List<User> findAllWithRoleId(Map<String, Object> map) throws ServiceException;
    //批量禁用
    boolean batchDisable(List<String> idList,Date loceDate) throws ServiceException;
    //批量启用
    boolean batchEnable(List<String> idList,Date loceDate) throws ServiceException;

    List<User> findAllOrgManagerByOrgId(Map<String, Object> map) throws ServiceException;
}
