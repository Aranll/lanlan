package com.xiaosuokeji.yilan.server.service.intf.security;


import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.security.SecOrganization;
import com.xiaosuokeji.yilan.server.model.security.SecResource;
import com.xiaosuokeji.yilan.server.model.security.SecRole;
import com.xiaosuokeji.yilan.server.model.security.SecStaff;

import java.util.List;
import java.util.Map;

/**
 * 系统安全Service<br/>
 * Created by xuxiaowei on 2017/5/22.
 */
public interface SecurityService {

    void createResource(SecResource resource) throws BusinessException;

    void deleteResource(SecResource resource) throws BusinessException;

    SecResource findResource(SecResource resource);

    void updateResource(SecResource resource) throws BusinessException;

    PageModel listResourceAndCount(SecResource resource);

    List<SecResource> listResourceTree(SecResource resource);

    void createRole(SecRole role) throws BusinessException;

    void deleteRole(SecRole role) throws BusinessException;

    SecRole findRole(SecRole role);

    void updateRole(SecRole role) throws BusinessException;

    PageModel listRoleAndCount(SecRole role);

    List<SecRole> listRolePair(SecRole role);

    List<SecResource> listResourceTreeByRole(SecRole role);

    void authorizeResourceToRole(SecRole role);

    void createStaff(SecStaff staff) throws BusinessException;

    void deleteStaff(SecStaff staff);

    SecStaff findStaff(SecStaff staff);

    void updateStaff(SecStaff staff) throws BusinessException;

    PageModel listStaffAndCount(SecStaff staff);

    List<SecRole> listRolePairByStaff(SecStaff staff);

    void authorizeRoleToStaff(SecStaff staff);

    void createOrganization(SecOrganization organization) throws BusinessException;

    void deleteOrganization(SecOrganization organization) throws BusinessException;

    SecOrganization findOrganization(SecOrganization organization);

    void updateOrganization(SecOrganization organization) throws BusinessException;

    PageModel listOrganizationAndCount(SecOrganization organization);

    List<SecOrganization> listOrgTree(SecOrganization organization);

    List<SecRole> listRolePairByOrganization(SecOrganization organization);

    void authorizeRoleToOrganization(SecOrganization organization);

    List<SecOrganization> listOrgTreeByStaff(SecStaff staff);

    void joinOrg(SecStaff staff);

    SecStaff findStaffByUserName(SecStaff staff) throws BusinessException;

    List<SecRole> listRoleByReq(SecResource resource);

    Map<String, String> findRolesByPkey(SecResource resource);

    SecResource findResourceByReq(SecResource secResource);
}
