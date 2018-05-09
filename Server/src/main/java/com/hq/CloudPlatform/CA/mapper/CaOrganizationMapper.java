package com.hq.CloudPlatform.CA.mapper;

import com.hq.CloudPlatform.CA.entity.CaOrgManager;
import com.hq.CloudPlatform.CA.entity.CaOrganization;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2017/3/7.
 */
@Repository
public interface CaOrganizationMapper extends BaseMapper<CaOrganization> {
    /**
     * 根据父id查询组织的相关信息
     *
     * @param id
     * @return
     */
    List<CaOrganization> findByParentId(String id);

    CaOrgManager findOrgManager(@Param("userId") String userId, @Param("orgId") String orgId);

    /**
     * 添加机构下的机构管理员
     *
     * @param om
     */
    void saveOrgManager(CaOrgManager om);

    /**
     * 查询是否存在子机构
     */
    int findCountByParentId(String id);

    /**
     * 删除机构下的机构管理员
     *
     * @param caOrgManager
     */
    void deleteOrgManager(CaOrgManager caOrgManager);

    /**
     * 禁用机构下的机构管理员
     *
     * @param caOrgManager
     */
    void batchDisableOrgManager(CaOrgManager caOrgManager);

    /**
     * 禁用机构下的机构管理员
     *
     * @param caOrgManager
     */
    void batchEnableOrgManager(CaOrgManager caOrgManager);

    /**
     * 根据父机构ID递归获取该机构下的子机构列表，子机构下的子机构也一并获取
     *
     * @param orgId
     * @return
     */
    List<CaOrganization> recursionFindByParentId(String orgId);
}
