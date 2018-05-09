package com.hq.CloudPlatform.CA.service;


import com.hq.CloudPlatform.CA.entity.CaOrgManager;
import com.hq.CloudPlatform.CA.entity.CaOrganization;
import com.hq.CloudPlatform.CA.exception.ServiceException;

import java.util.List;

/**
 * Created by admin on 2017/3/7.
 */
public interface ICaOrganizationService extends IBaseService<CaOrganization> {

    /**
     * 根据父节点ID查询其直接子机构的列表信息
     *
     * @param id
     * @return
     */
    List<CaOrganization> findByParentId(String id) throws ServiceException;

    /**
     * 根据父节点ID递归查询其直接子机构的列表信息，其子机构下的子机构也一并查询出来
     *
     * @param id
     * @return
     */
    List<CaOrganization> recursionFindByParentId(String id) throws ServiceException;

    CaOrgManager getOrgManager(String userId, String orgId) throws ServiceException;

    void saveOrgManager(CaOrgManager om) throws ServiceException;

    /**
     * 删除机构下的机构管理员
     *
     * @param caOrgManager
     * @return
     * @throws ServiceException
     */
    boolean deleteOrgManager(CaOrgManager caOrgManager) throws ServiceException;

    /**
     * 批量禁用机构下的管理员
     *
     * @param caOrgManager
     * @return
     * @throws ServiceException
     */
    boolean batchDisableOrgManager(CaOrgManager caOrgManager) throws ServiceException;

    /**
     * 查询是否存在子机构
     */
    int findCountByParentId(String id) throws ServiceException;

    /**
     * 批量启用机构下的管理员
     *
     * @param caOrgManager
     * @return
     * @throws ServiceException
     */
    boolean batchEnableOrgManager(CaOrgManager caOrgManager) throws ServiceException;
}
