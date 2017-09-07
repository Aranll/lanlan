package com.xiaosuokeji.yilan.server.dao.security;


import com.xiaosuokeji.yilan.server.model.security.SecOrganization;
import com.xiaosuokeji.yilan.server.model.security.SecResource;
import com.xiaosuokeji.yilan.server.model.security.SecRole;
import com.xiaosuokeji.yilan.server.model.security.SecStaff;

import java.util.List;

/**
 * 系统安全Dao<br/>
 * Created by xuxiaowei 2017/5/22.
 */
public interface SecurityDao {

    int insertResource(SecResource resource);

    int deleteResource(SecResource resource);

    SecResource findResource(SecResource resource);

    int updateResource(SecResource resource);

    List<SecResource> listResource(SecResource resource);

    Long countResource(SecResource resource);

    SecResource findResourceByKey(SecResource resource);

    Long countRoleByRes(SecResource resource);

    List<SecResource> listResourceTree(SecResource resource);

    int insertRole(SecRole role);

    int deleteRole(SecRole role);

    SecRole findRole(SecRole role);

    int updateRole(SecRole role);

    List<SecRole> listRole(SecRole role);

    Long countRole(SecRole role);

    SecRole findRoleByKey(SecRole role);

    List<SecRole> listRolePair(SecRole role);

    Long countStaffByRole(SecRole role);

    Long countOrgByRole(SecRole role);

    List<SecResource> listResourceByRole(SecRole role);

    void insertRoleRes(SecRole role);

    void deleteRoleRes(SecRole role);

    void deleteRoleResByRole(SecRole role);

    int insertStaff(SecStaff staff);

    int deleteStaff(SecStaff staff);

    SecStaff findStaff(SecStaff staff);

    int updateStaff(SecStaff staff);

    List<SecStaff> listStaff(SecStaff staff);

    Long countStaff(SecStaff staff);

    SecStaff findStaffByKey(SecStaff staff);

    List<SecRole> listRoleByStaff(SecStaff secStaff);

    void insertStaffRole(SecStaff secStaff);

    void deleteStaffRole(SecStaff secStaff);

    void deleteStaffRoleByStaff(SecStaff secStaff);

    int insertOrganization(SecOrganization organization);

    int deleteOrganization(SecOrganization organization);

    SecOrganization findOrganization(SecOrganization organization);

    int updateOrganization(SecOrganization organization);

    List<SecOrganization> listOrganization(SecOrganization organization);

    Long countOrganization(SecOrganization organization);

    SecOrganization findOrganizationByKey(SecOrganization organization);

    List<SecOrganization> listOrgTree(SecOrganization organization);

    Long countStaffByOrg(SecOrganization organization);

    List<SecRole> listRoleByOrg(SecOrganization organization);

    void insertOrgRole(SecOrganization organization);

    void deleteOrgRole(SecOrganization organization);

    void deleteOrgRoleByOrg(SecOrganization organization);

    List<SecOrganization> listOrgByStaff(SecStaff staff);

    void insertStaffOrg(SecStaff staff);

    void deleteStaffOrg(SecStaff staff);

    void deleteStaffOrgByStaff(SecStaff staff);

    SecStaff findStaffByUsername(SecStaff staff);

    List<SecRole> listRoleByReq(SecResource resource);

    List<SecResource> listRoleByResPid(SecResource resource);

    List<SecResource> listRoleByResPkey(SecResource resource);

    SecResource findResourceByReq(SecResource secResource);
}
