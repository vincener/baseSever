package com.hq.CloudPlatform.CA.restful.impl;

import com.alibaba.fastjson.JSON;
import com.hq.CloudPlatform.CA.annotations.AttributeCheck;
import com.hq.CloudPlatform.CA.entity.CaOrgManager;
import com.hq.CloudPlatform.CA.entity.CaOrganization;
import com.hq.CloudPlatform.CA.entity.User;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.restful.ICaOrganizationRestService;
import com.hq.CloudPlatform.CA.restful.view.JsonViewObject;
import com.hq.CloudPlatform.CA.service.IBaseService;
import com.hq.CloudPlatform.CA.service.ICaOrganizationService;
import com.hq.CloudPlatform.CA.service.IUserService;
import com.hq.CloudPlatform.CA.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by WY
 * Path ${PACKAGE_NAME}
 * Created Date 2017/3/7.
 */
@Path("caorg")
@Component
@Slf4j
public class CaOrganizationRestServiceImpl extends BaseRestServiceImpl<CaOrganization> implements ICaOrganizationRestService {

    @Autowired
    private ICaOrganizationService caOrganizationService;

	@Autowired
	private IUserService userService;

    private AttributeCheck<CaOrganization> check = new AttributeCheck<>();

    @Override
	public IBaseService<CaOrganization> getService() {
        return caOrganizationService;
    }

    /**
     * 新增组织机构
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String save(String jsonStr) {
		JsonViewObject jsonView = new JsonViewObject();
        CaOrganization entity = JSON.parseObject(jsonStr, this.getEntityClass());
        String msg = check.CheckString(entity);
        if (msg != null) {
            jsonView.failPack("false", msg);
        } else {
            try {
                if (entity != null) {
                    entity.setIssuer(((User) request.getSession().getAttribute(Constants.SESSION_KEY_USER)).getUsername());
                    this.getService().save(entity);
                    String id = this.getService().findByName(entity.getName()).getId();

                    if ("exists".equals(id)) {
                        jsonView.setMessage("exists");
                    } else {
                        jsonView.successPack(id);
                    }
                }
            } catch (UnauthorizedException unauthorizedException) {
                jsonView.unauthorizedPack();
            } catch (Exception e) {
                String message = e.getMessage();

                if (StringUtils.isBlank(message)) {
                    message = "保存数据失败！";
                }
                jsonView.failPack("false", message);
                log.error("CaOrganizationRestServiceImpl save is error,{jsonStr:" + jsonStr + "}," + e.getMessage(), e);
            }
        }
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 通过传递的parent_id值，查询符合该条件的所有机构信息
     *
     * @param id
     * @return
     */
    @Override
    public String findByParentId(String id) {
		JsonViewObject jsonView = new JsonViewObject();
        try {
            List<CaOrganization> list = caOrganizationService.findByParentId(id);
            jsonView.successPack(list);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("BaseRestServiceImpl getAll is error", e);
        }
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

	/**
	 * 保存机构下的管理员
	 * @param jsonStr
     * @return
     */
	@Override
	public String saveOrgManager(String jsonStr) {
		JsonViewObject jsonView = new JsonViewObject();
		try {
			Object o = ((User) request.getSession().getAttribute(Constants.SESSION_KEY_USER));
			if (o != null) {
				if (!StringUtils.isBlank(jsonStr)) {
					Map<String, Object> map = JSON.parseObject(jsonStr);
					String orgId = (String) map.get("orgId");
					List<String> userIdList = (List<String>) map.get("userId");
					String issuer = ((User) o).getUsername();
					String creatorId = ((User) o).getId();
					CaOrgManager om;
					Map<String, Object> orgManagermap = new HashedMap();
					orgManagermap.put("orgId",orgId);
					//取两个集合的差集，删除那些不勾选而在数据库中已经有的user
					List<User> list = userService.findAllOrgManagerByOrgId(orgManagermap);
					List<String> uidList=new ArrayList<String>();
					for(User user:list){
						uidList.add(user.getId());

					}
					uidList.removeAll(userIdList);
					if(uidList != null){
						for(String uid:uidList){
							om = new CaOrgManager();
							om.setOrgId(orgId);
							om.setUserId(uid);
							om.setDeleterId(creatorId);
							om.setDeleterName(issuer);
							om.setDeleteDate(new Date());
							caOrganizationService.deleteOrgManager(om);
						}
					}
					//取两个集合的差集，新增数据库不存在的user
					List<User> listUser = userService.findAllOrgManagerByOrgId(orgManagermap);
					List<String> idList=new ArrayList<String>();
					for(User user:listUser){
						idList.add(user.getId());

					}

					userIdList.removeAll(idList);
					if(userIdList != null){
						for(String userId:userIdList){
							om = new CaOrgManager();
							om.setCreatorId(creatorId);
							om.setCreatorName(issuer);
							om.setOrgId(orgId);
							om.setUserId(userId);
							caOrganizationService.saveOrgManager(om);
						}
					}
					jsonView.successPack("true", "修改成功！");
				}
			} else {
				jsonView.failPack("false", "登陆失效！");
			}
		} catch (UnauthorizedException unauthorizedException) {
			jsonView.unauthorizedPack();
		} catch (Exception e) {
//             String message = e.getMessage();

//             if (StringUtils.isBlank(message)) {
//                 message = "保存数据失败！";
//             }
			jsonView.failPack("false", "保存数据失败！");
			log.error("BaseRestServiceImpl save is error,{jsonStr:" + jsonStr + "}," + e.getMessage(), e);
		}
		return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 删除机构下的机构管理员
     */
	@Override
	public String deleteOrgManager(String jsonStr) throws ServiceException {
		JsonViewObject jsonView = new JsonViewObject();
		boolean flag = false;
		try {
			if (!StringUtils.isBlank(jsonStr)) {
				Map<String, Object> map = JSON.parseObject(jsonStr);
				String userId = (String) map.get("userId");
				String orgId = (String) map.get("orgId");
				Object o = ((User) request.getSession().getAttribute(Constants.SESSION_KEY_USER));
				String issuer = ((User) o).getUsername();
				String id = ((User) o).getId();
				CaOrgManager caOrgManager = new CaOrgManager();
				caOrgManager.setOrgId(orgId);
				caOrgManager.setUserId(userId);
				caOrgManager.setDeleterId(id);
				caOrgManager.setDeleterName(issuer);
				caOrgManager.setDeleteDate(new Date());
				flag = caOrganizationService.deleteOrgManager(caOrgManager);
			}
		} catch (UnauthorizedException unauthorizedException) {
			jsonView.unauthorizedPack();
		} catch (Exception e) {
			jsonView.failPack(JSON.toJSONString(flag));
			log.error("CaOrganizationRestServiceImpl deleteOrgManager is error,{jsonStr:" + jsonStr + "}", e);
		}

		return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public String findCountByParentId(String id) {
		JsonViewObject jsonView = new JsonViewObject();
		try {
			int count = caOrganizationService.findCountByParentId(id);
			if (count > 0) {
				jsonView.failPack("fail");
			} else {
				jsonView.successPack("success");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			jsonView.failPack(e);
		}
		return JSON.toJSONString(jsonView);
	}

	/**
	 *批量删除机构下的机构管理员
	 * @throws ServiceException
     */
	@Override
	public String batchDeleteOrgManager(String jsonStr) throws ServiceException {
		JsonViewObject jsonView = new JsonViewObject();
		boolean flag = false;
		try {
			if (!StringUtils.isBlank(jsonStr)) {
				Map<String, Object> map = JSON.parseObject(jsonStr);
				String orgId = (String) map.get("orgId");
				List<String> userIdList = (List<String>) map.get("userId");
				Object o = ((User) request.getSession().getAttribute(Constants.SESSION_KEY_USER));
				String issuer = ((User) o).getUsername();
				String id = ((User) o).getId();
				CaOrgManager caOrgManager = new CaOrgManager();
				for(String userId:userIdList){
					caOrgManager.setOrgId(orgId);
					caOrgManager.setUserId(userId);
					caOrgManager.setDeleterId(id);
					caOrgManager.setDeleterName(issuer);
					caOrgManager.setDeleteDate(new Date());
					flag = caOrganizationService.deleteOrgManager(caOrgManager);
				}

			}
		} catch (UnauthorizedException unauthorizedException) {
			jsonView.unauthorizedPack();
		} catch (Exception e) {
			jsonView.failPack(JSON.toJSONString(flag));
			log.error("CaOrganizationRestServiceImpl batchDeleteOrgManager is error,{jsonStr:" + jsonStr + "}", e);
		}

		return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public String batchDisableOrgManager(String jsonStr) {
		JsonViewObject jsonView = new JsonViewObject();
		boolean flag = false;
		try {
			if (!StringUtils.isBlank(jsonStr)) {
				Map<String, Object> map = JSON.parseObject(jsonStr);
				String orgId = (String) map.get("orgId");
				List<String> userIdList = (List<String>) map.get("userId");
				Object o = ((User) request.getSession().getAttribute(Constants.SESSION_KEY_USER));
				String issuer = ((User) o).getUsername();
				String id = ((User) o).getId();
				CaOrgManager caOrgManager = new CaOrgManager();
				for(String userId:userIdList){
					caOrgManager.setOrgId(orgId);
					caOrgManager.setUserId(userId);
					caOrgManager.setLockerId(id);
					caOrgManager.setLockerName(issuer);
					caOrgManager.setLockDate(new Date());
					flag = caOrganizationService.batchDisableOrgManager(caOrgManager);
					jsonView.successPack(JSON.toJSONString(flag));
				}

			}
		} catch (UnauthorizedException unauthorizedException) {
			jsonView.unauthorizedPack();
		} catch (Exception e) {
			jsonView.failPack(JSON.toJSONString(flag));
			log.error("CaOrganizationRestServiceImpl batchDisableOrgManager is error,{jsonStr:" + jsonStr + "}", e);
		}

		return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public String batchEnableOrgManager(String jsonStr) {
		JsonViewObject jsonView = new JsonViewObject();
		boolean flag = false;
		try {
			if (!StringUtils.isBlank(jsonStr)) {
				Map<String, Object> map = JSON.parseObject(jsonStr);
				String orgId = (String) map.get("orgId");
				List<String> userIdList = (List<String>) map.get("userId");
				Object o = ((User) request.getSession().getAttribute(Constants.SESSION_KEY_USER));
				String issuer = ((User) o).getUsername();
				String id = ((User) o).getId();
				CaOrgManager caOrgManager = new CaOrgManager();
				for(String userId:userIdList){
					caOrgManager.setOrgId(orgId);
					caOrgManager.setUserId(userId);
					caOrgManager.setLockerId(null);
					caOrgManager.setLockerName(null);
					caOrgManager.setLockDate(null);
					flag = caOrganizationService.batchEnableOrgManager(caOrgManager);
					jsonView.successPack(JSON.toJSONString(flag));
				}

			}
		} catch (UnauthorizedException unauthorizedException) {
			jsonView.unauthorizedPack();
		} catch (Exception e) {
			jsonView.failPack(JSON.toJSONString(flag));
			log.error("CaOrganizationRestServiceImpl batchEnableOrgManager is error,{jsonStr:" + jsonStr + "}", e);
		}

		return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
	}
}
