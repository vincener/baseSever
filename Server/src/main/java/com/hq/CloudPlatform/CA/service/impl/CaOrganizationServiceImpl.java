package com.hq.CloudPlatform.CA.service.impl;

import com.hq.CloudPlatform.CA.entity.CaOrgManager;
import com.hq.CloudPlatform.CA.entity.CaOrganization;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.mapper.BaseMapper;
import com.hq.CloudPlatform.CA.mapper.CaOrganizationMapper;
import com.hq.CloudPlatform.CA.service.ICaOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by admin on 2017/3/7.
 */
@Service
public class CaOrganizationServiceImpl extends BaseServiceImpl<CaOrganization> implements ICaOrganizationService {

    @Autowired
    private CaOrganizationMapper organizationMapper;

    public BaseMapper<CaOrganization> getBaseMapper() {
        return organizationMapper;
    }

    @Override
    public List<CaOrganization> findAll() throws ServiceException {
        return super.findAll();
    }

    @Override
    public List<CaOrganization> findByParentId(String id) throws ServiceException {
        List<CaOrganization> list;

        try {
            list = organizationMapper.findByParentId(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return list;
    }

    @Override
    public List<CaOrganization> recursionFindByParentId(String id) throws ServiceException {
        List<CaOrganization> list;

        try {
            list = organizationMapper.recursionFindByParentId(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return list;
    }

    @Override
    public CaOrgManager getOrgManager(String userId, String orgId) throws ServiceException {
        CaOrgManager cam;
        try {
            cam = organizationMapper.findOrgManager(userId, orgId);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return cam;
    }

    @Override
    public void saveOrgManager(CaOrgManager om) throws ServiceException {
        om.setCreateDate(new Date());
        organizationMapper.saveOrgManager(om);
    }

    @Override
    public boolean deleteOrgManager(CaOrgManager caOrgManager) throws ServiceException {
        try {
            organizationMapper.deleteOrgManager(caOrgManager);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public boolean batchDisableOrgManager(CaOrgManager caOrgManager) throws ServiceException {
        try {
            organizationMapper.batchDisableOrgManager(caOrgManager);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public int findCountByParentId(String id) throws ServiceException {
        int count;
        try {
            count = organizationMapper.findCountByParentId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return count;
    }

    @Override
    public boolean batchEnableOrgManager(CaOrgManager caOrgManager) throws ServiceException {
        try {
            organizationMapper.batchEnableOrgManager(caOrgManager);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }
}
