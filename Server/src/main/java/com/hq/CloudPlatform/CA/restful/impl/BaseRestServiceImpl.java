package com.hq.CloudPlatform.CA.restful.impl;

import com.alibaba.fastjson.JSON;
import com.hq.CloudPlatform.CA.entity.BaseEntity;
import com.hq.CloudPlatform.CA.restful.IBaseRestService;
import com.hq.CloudPlatform.CA.restful.view.JsonViewObject;
import com.hq.CloudPlatform.CA.restful.view.Page;
import com.hq.CloudPlatform.CA.service.IBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 * @date 2017/3/7
 */
@Slf4j
public abstract class BaseRestServiceImpl<Entity extends BaseEntity> implements IBaseRestService {

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpServletRequest request;

    /**
     * 获取ServiceBean
     *
     * @return
     */
    public abstract IBaseService<Entity> getService();

    /**
     * 分页获取
     * <p>
     * 请求参数样例：
     * {
     * "pageSize": 10,
     * "pageNumber": 1,
     * "conditions": {
     * "jobNum": "No.113"
     * }
     * }
     * <p>
     * 响应样例：
     * {
     * "content": {
     * "conditions": {
     * "jobNum": "No.113"
     * },
     * "pageNumber": 1,
     * "pageSize": 10,
     * "startRowNum": 0,
     * "endRowNum": 10,
     * "total": 1,
     * "totalPageNum": 1,
     * "rows": [
     * {
     * "id": "C5F0CA07EA864022B1BF072DB4161119",
     * "jobNum": "No.113",
     * "loginName": "lisi",
     * "orgId": "5",
     * "password": "e10adc3949ba59abbe56e057f20f883e",
     * "roleList": [],
     * "username": "李四"
     * }
     * ]
     * },
     * "message": "",
     * "status": "success"
     * }
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String getPage(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        Page page;

        try {
            if (!StringUtils.isBlank(jsonStr)) {
                page = JSON.parseObject(jsonStr, Page.class);
            } else {
                page = new Page();
            }

            page = this.getService().findByPage(page);
            jsonView.successPack(page);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("BaseRestServiceImpl getPage is error,{jsonStr:" + jsonStr + "}", e);
        }

        //return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteMapNullValue);
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 查询该接口下的所有信息
     *
     * @return
     */
    @Override
    public String getAll() {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            List<Entity> list = this.getService().findAll();
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
     * 根据不同的条件进行查询
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String getByWhere(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            Map<String, Object> mapBean = JSON.parseObject(jsonStr);
            List<Entity> list = this.getService().findByMap(mapBean);
            if (list.size() != 0) {
                jsonView.successPack(list);
            } else {
                jsonView.failPack("fail");
            }

        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("BaseRestServiceImpl getByWhere is error，{jsonStr:" + jsonStr + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 通过传递的id值进行查询相关信息
     *
     * @param id
     * @return
     */
    @Override
    public String getById(String id) {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            if (StringUtils.isNotBlank(id)) {
                Entity entity = this.getService().findById(id);
                jsonView.successPack(entity);
            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("BaseRestServiceImpl getById is error,{id:" + id + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 通过传递的id值删除该id下的记录
     *
     * @param id
     * @return
     */
    @Override
    public String deleteById(String id) {
        JsonViewObject jsonView = new JsonViewObject();
        boolean flag = false;

        try {
            flag = this.getService().deleteById(id);
            jsonView.successPack(JSON.toJSONString(flag));
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(JSON.toJSONString(flag));
            log.error("BaseRestServiceImpl deleteById is error,{Id:" + id + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 批量删除
     * 请求参数样例：["id1","id2","id3","id4"]
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String batchDelete(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        boolean flag = false;

        try {
            List<String> idList = JSON.parseArray(jsonStr, String.class);
            if (idList.size() <= 0) {
                jsonView.successPack("false");
            } else {
                flag = this.getService().batchDelete(idList);
                jsonView.successPack(JSON.toJSONString(flag));
            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(JSON.toJSONString(flag));
            log.error("BaseRestServiceImpl deleteById is error," + jsonStr, e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 实现新增
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String save(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            Entity entity = JSON.parseObject(jsonStr, this.getEntityClass());

            if (entity != null) {
                String id = this.getService().save(entity);

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
            log.error("BaseRestServiceImpl save is error,{jsonStr:" + jsonStr + "}," + e.getMessage(), e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 修改记录
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String update(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            Entity entity = JSON.parseObject(jsonStr, this.getEntityClass());

            if (entity != null) {
                jsonView.successPack(this.getService().update(entity));
            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            String message = e.getMessage();

            if (StringUtils.isBlank(message)) {
                message = "更新数据失败！";
            }

            jsonView.failPack("false", message);
            log.error("BaseRestServiceImpl update is error,{jsonStr:" + jsonStr + "}," + e.getMessage(), e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据不同的条件进行查询在编辑时做验证
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String checkByWhere(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            Map<String, Object> mapBean = JSON.parseObject(jsonStr);
            List<Entity> list = this.getService().checkByMap(mapBean);
            if (list.size() != 0) {
                jsonView.successPack(list);
            } else {
                jsonView.failPack("fail");
            }

        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("BaseRestServiceImpl getByWhere is error，{jsonStr:" + jsonStr + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前的对象class
     *
     * @return
     */
    public Class<Entity> getEntityClass() {
        return (Class<Entity>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }


}
