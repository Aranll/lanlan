package com.xiaosuokeji.yilan.server.service.impl.security;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.security.SecurityConsts;
import com.xiaosuokeji.yilan.server.dao.security.SecurityDao;
import com.xiaosuokeji.yilan.server.model.security.SecOrganization;
import com.xiaosuokeji.yilan.server.model.security.SecResource;
import com.xiaosuokeji.yilan.server.model.security.SecRole;
import com.xiaosuokeji.yilan.server.model.security.SecStaff;
import com.xiaosuokeji.yilan.server.service.intf.security.SecurityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统安全ServiceImpl<br/>
 * Created by xuxiaowei on 2017/5/22.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private SecurityDao securityDao;

    @Override
    @Transactional
    public void createResource(SecResource resource) throws BusinessException {
        if (securityDao.findResourceByKey(resource) != null) throw new BusinessException(SecurityConsts.RESOURCE_EXIST);
        securityDao.insertResource(resource);
        //授权资源给超级管理员角色
        SecRole role = new SecRole(1L);
        List<SecResource> resources = new ArrayList<>();
        resources.add(resource);
        role.setResources(resources);
        securityDao.insertRoleRes(role);
    }

    @Override
    @Transactional
    public void deleteResource(SecResource resource) throws BusinessException {
        if (securityDao.countRoleByRes(resource) > 0L) throw new BusinessException(SecurityConsts.RESOURCE_USED);
        SecResource pRes = new SecResource();
        pRes.setPid(resource.getId());
        if (securityDao.countResource(pRes) > 0L) throw new BusinessException(SecurityConsts.RESOURCE_USED);
        //为超级管理员角色删除资源
        SecRole role = new SecRole(1L);
        List<SecResource> resources = new ArrayList<>();
        resources.add(resource);
        role.setResources(resources);
        securityDao.deleteRoleRes(role);
        securityDao.deleteResource(resource);
    }

    @Override
    public SecResource findResource(SecResource resource) {
        return securityDao.findResource(resource);
    }

    @Override
    public void updateResource(SecResource resource) throws BusinessException {
        if (securityDao.findResourceByKey(resource) != null) throw new BusinessException(SecurityConsts.RESOURCE_EXIST);
        securityDao.updateResource(resource);
    }

    @Override
    public PageModel listResourceAndCount(SecResource resource) {
        resource.setDefaultSort(new String[]{"type","seq","id"}, new String[]{"ASC","DESC","ASC"});
        return PageModel.build(securityDao.listResource(resource), securityDao.countResource(resource));
    }

    @Override
    public List<SecResource> listResourceTree(SecResource resource) {
        List<SecResource> result = new ArrayList<>();
        List<SecResource> resources = securityDao.listResourceTree(resource);
        Map<Long, SecResource> temp = new HashMap<>();
        for (SecResource item : resources) temp.put(item.getId(), item);
        //遍历资源集合，调整资源树状层级关系
        for (Long id : temp.keySet()) {
            //若无父级则放入结果列表中，若有父级则放入父级子列表中
            if (temp.get(id).getPid().equals(0L)) result.add(temp.get(id));
            else temp.get(temp.get(id).getPid()).addSon(temp.get(id));
        }
        return result;
    }

    @Override
    public void createRole(SecRole role) throws BusinessException {
        if (securityDao.findRoleByKey(role) != null) throw new BusinessException(SecurityConsts.ROLE_EXIST);
        securityDao.insertRole(role);
    }

    @Override
    @Transactional
    public void deleteRole(SecRole role) throws BusinessException {
        if (securityDao.countStaffByRole(role) > 0L) throw new BusinessException(SecurityConsts.ROLE_USED);
        if (securityDao.countOrgByRole(role) > 0L) throw new BusinessException(SecurityConsts.ROLE_USED);
        if (securityDao.deleteRole(role) == 1) securityDao.deleteRoleResByRole(role);
    }

    @Override
    public SecRole findRole(SecRole role) {
        //禁止查询超级管理员角色
        role.setSuperior(0);
        return securityDao.findRole(role);
    }

    @Override
    public void updateRole(SecRole role) throws BusinessException {
        if (securityDao.findRoleByKey(role) != null) throw new BusinessException(SecurityConsts.ROLE_EXIST);
        securityDao.updateRole(role);
    }

    @Override
    public PageModel listRoleAndCount(SecRole role) {
        return PageModel.build(securityDao.listRole(role), securityDao.countRole(role));
    }

    @Override
    public List<SecRole> listRolePair(SecRole role) {
        return securityDao.listRolePair(role);
    }

    @Override
    public List<SecResource> listResourceTreeByRole(SecRole role) {
        List<SecResource> result = new ArrayList<>();
        //禁止查询超级管理员角色资源树
        if (securityDao.findRole(role).getSuperior().equals(1)) return result;
        List<SecResource> resources = securityDao.listResourceTree(new SecResource());
        List<SecResource> ownedResources = securityDao.listResourceByRole(role);
        Map<Long, SecResource> temp = new HashMap<>();
        for (SecResource item : resources) temp.put(item.getId(), item);
        //遍历资源集合，调整资源树状层级关系
        for (Long id : temp.keySet()) {
            //若无父级则放入结果列表中，若有父级则放入父级子列表中
            if (temp.get(id).getPid().equals(0L)) result.add(temp.get(id));
            else temp.get(temp.get(id).getPid()).addSon(temp.get(id));
            //遍历该角色拥有的资源列表，若当前资源存在于拥有列表中则设置其被选择
            temp.get(id).setChecked(0);
            for (SecResource ownedItem : ownedResources) {
                if (ownedItem.getId().equals(id)) {
                    temp.get(id).setChecked(1);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public void authorizeResourceToRole(SecRole role) {
        //禁止修改超级管理员资源
        if (securityDao.findRole(role).getSuperior().equals(1)) return;
        List<SecResource> oldReses = securityDao.listResourceByRole(role);
        List<SecResource> newReses = new ArrayList<>();
        List<SecResource> noChangedReses = new ArrayList<>();
        //查询出新增的资源列表和未变动的资源列表
        if (role.getResources() != null) {
            for (int i=0; i<role.getResources().size(); ++i) {
                int j=0;
                for (; j<oldReses.size(); ++j) {
                    if (role.getResources().get(i).getId().equals(oldReses.get(j).getId())) {
                        noChangedReses.add(oldReses.get(j));
                        break;
                    }
                }
                if (j >= oldReses.size()) newReses.add(role.getResources().get(i));
            }
        }
        //新增资源
        if (newReses.size() > 0) {
            role.setResources(newReses);
            securityDao.insertRoleRes(role);
        }
        //从旧资源中去除未变动的资源从而获取需要删除的资源
        oldReses.removeAll(noChangedReses);
        //删除资源
        if (oldReses.size() > 0) {
            role.setResources(oldReses);
            securityDao.deleteRoleRes(role);
        }
    }

    @Override
    public void createStaff(SecStaff staff) throws BusinessException {
        if (securityDao.findStaffByKey(staff) != null) throw new BusinessException(SecurityConsts.STAFF_EXIST);
        securityDao.insertStaff(staff);
    }

    @Override
    @Transactional
    public void deleteStaff(SecStaff staff) {
        if (securityDao.deleteStaff(staff) == 1) {
            securityDao.deleteStaffRoleByStaff(staff);
            securityDao.deleteStaffOrgByStaff(staff);
        }
    }

    @Override
    public SecStaff findStaff(SecStaff staff) {
        SecStaff result = securityDao.findStaff(staff);
        //隐藏密码
        if (result != null) result.setPassword(null);
        return result;
    }

    @Override
    public void updateStaff(SecStaff staff) throws BusinessException {
        if (securityDao.findStaffByKey(staff) != null) throw new BusinessException(SecurityConsts.STAFF_EXIST);
        securityDao.updateStaff(staff);
    }

    @Override
    public PageModel listStaffAndCount(SecStaff staff) {
        return PageModel.build(securityDao.listStaff(staff), securityDao.countStaff(staff));
    }

    @Override
    public List<SecRole> listRolePairByStaff(SecStaff staff) {
        //禁止查询超级管理员角色
        if (securityDao.findStaff(staff).getSuperior().equals(1)) return new ArrayList<>();
        List<SecRole> roles = securityDao.listRolePair(new SecRole());
        List<SecRole> ownedRoles = securityDao.listRoleByStaff(staff);
        for (SecRole item : roles) {
            //遍历该员工拥有的角色列表，若当前角色存在于拥有列表中则设置其被选择
            item.setChecked(0);
            for (SecRole ownedItem : ownedRoles) {
                if (ownedItem.getId().equals(item.getId())) {
                    item.setChecked(1);
                    break;
                }
            }
        }
        return roles;
    }

    @Override
    @Transactional
    public void authorizeRoleToStaff(SecStaff staff) {
        //禁止修改超级管理员角色
        if (securityDao.findStaff(staff).getSuperior().equals(1)) return;
        List<SecRole> oldRoles = securityDao.listRoleByStaff(staff);
        List<SecRole> newRoles = new ArrayList<>();
        List<SecRole> noChangedRoles = new ArrayList<>();
        //查询出新增的角色列表和未变动的角色列表
        if (staff.getRoles() != null) {
            for (int i=0; i<staff.getRoles().size(); ++i) {
                int j=0;
                for (; j<oldRoles.size(); ++j) {
                    if (staff.getRoles().get(i).getId().equals(oldRoles.get(j).getId())) {
                        noChangedRoles.add(oldRoles.get(j));
                        break;
                    }
                }
                if (j >= oldRoles.size()) newRoles.add(staff.getRoles().get(i));
            }
        }
        //新增角色
        if (newRoles.size() > 0) {
            staff.setRoles(newRoles);
            securityDao.insertStaffRole(staff);
        }
        //从旧资源中去除未变动的角色从而获取需要删除的角色
        oldRoles.removeAll(noChangedRoles);
        //删除角色
        if (oldRoles.size() > 0) {
            staff.setRoles(oldRoles);
            securityDao.deleteStaffRole(staff);
        }
    }

    @Override
    public void createOrganization(SecOrganization organization) throws BusinessException {
        if (securityDao.findOrganizationByKey(organization) != null) throw new BusinessException(SecurityConsts.ORGANIZATION_EXIST);
        securityDao.insertOrganization(organization);
    }

    @Override
    @Transactional
    public void deleteOrganization(SecOrganization organization) throws BusinessException {
        if (securityDao.countStaffByOrg(organization) > 0L) throw new BusinessException(SecurityConsts.ORGANIZATION_USED);
        securityDao.deleteOrgRoleByOrg(organization);
        securityDao.deleteOrganization(organization);
    }

    @Override
    public SecOrganization findOrganization(SecOrganization organization) {
        return securityDao.findOrganization(organization);
    }

    @Override
    public void updateOrganization(SecOrganization organization) throws BusinessException {
        if (securityDao.findOrganizationByKey(organization) != null) throw new BusinessException(SecurityConsts.ORGANIZATION_EXIST);
        securityDao.updateOrganization(organization);
    }

    @Override
    public PageModel listOrganizationAndCount(SecOrganization organization) {
        return PageModel.build(securityDao.listOrganization(organization), securityDao.countOrganization(organization));
    }

    @Override
    public List<SecOrganization> listOrgTree(SecOrganization organization) {
        List<SecOrganization> result = new ArrayList<>();
        List<SecOrganization> orgs = securityDao.listOrgTree(organization);
        Map<Long, SecOrganization> temp = new HashMap<>();
        for (SecOrganization item : orgs) temp.put(item.getId(), item);
        //遍历组织集合，调整组织树状层级关系
        for (Long id : temp.keySet()) {
            //若无父级则放入结果列表中，若有父级则放入父级子列表中
            if (temp.get(id).getPid().equals(0L)) result.add(temp.get(id));
            else temp.get(temp.get(id).getPid()).addSon(temp.get(id));
        }
        return result;
    }

    @Override
    public List<SecRole> listRolePairByOrganization(SecOrganization organization) {
        List<SecRole> roles = securityDao.listRolePair(new SecRole());
        List<SecRole> ownedRoles = securityDao.listRoleByOrg(organization);
        for (SecRole item : roles) {
            //遍历该组织拥有的角色列表，若当前角色存在于拥有列表中则设置其被选择
            item.setChecked(0);
            for (SecRole ownedItem : ownedRoles) {
                if (ownedItem.getId().equals(item.getId())) {
                    item.setChecked(1);
                    break;
                }
            }
        }
        return roles;
    }

    @Override
    @Transactional
    public void authorizeRoleToOrganization(SecOrganization organization) {
        List<SecRole> oldRoles = securityDao.listRoleByOrg(organization);
        List<SecRole> newRoles = new ArrayList<>();
        List<SecRole> noChangedRoles = new ArrayList<>();
        //查询出新增的角色列表和未变动的角色列表
        if (organization.getRoles() != null) {
            for (int i=0; i<organization.getRoles().size(); ++i) {
                int j=0;
                for (; j<oldRoles.size(); ++j) {
                    if (organization.getRoles().get(i).getId().equals(oldRoles.get(j).getId())) {
                        noChangedRoles.add(oldRoles.get(j));
                        break;
                    }
                }
                if (j >= oldRoles.size()) newRoles.add(organization.getRoles().get(i));
            }
        }
        //新增角色
        if (newRoles.size() > 0) {
            organization.setRoles(newRoles);
            securityDao.insertOrgRole(organization);
        }
        //从旧资源中去除未变动的角色从而获取需要删除的角色
        oldRoles.removeAll(noChangedRoles);
        //删除角色
        if (oldRoles.size() > 0) {
            organization.setRoles(oldRoles);
            securityDao.deleteOrgRole(organization);
        }
    }

    @Override
    public List<SecOrganization> listOrgTreeByStaff(SecStaff staff) {
        List<SecOrganization> result = new ArrayList<>();
        List<SecOrganization> orgs = securityDao.listOrgTree(new SecOrganization());
        List<SecOrganization> ownedOrgs = securityDao.listOrgByStaff(staff);
        Map<Long, SecOrganization> temp = new HashMap<>();
        for (SecOrganization item : orgs) temp.put(item.getId(), item);
        //遍历组织集合，调整组织树状层级关系
        for (Long id : temp.keySet()) {
            //若无父级则放入结果列表中，若有父级则放入父级子列表中
            if (temp.get(id).getPid().equals(0L)) result.add(temp.get(id));
            else temp.get(temp.get(id).getPid()).addSon(temp.get(id));
            //遍历该员工拥有的组织列表，若当前组织存在于拥有列表中则设置其被选择
            temp.get(id).setChecked(0);
            for (SecOrganization ownedItem : ownedOrgs) {
                if (ownedItem.getId().equals(id)) {
                    temp.get(id).setChecked(1);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public void joinOrg(SecStaff staff) {
        List<SecOrganization> oldOrgs = securityDao.listOrgByStaff(staff);
        List<SecOrganization> newOrgs = new ArrayList<>();
        List<SecOrganization> noChangedOrgs = new ArrayList<>();
        //查询出新增的组织列表和未变动的组织列表
        if (staff.getOrganizations() != null) {
            for (int i=0; i<staff.getOrganizations().size(); ++i) {
                int j=0;
                for (; j<oldOrgs.size(); ++j) {
                    if (staff.getOrganizations().get(i).getId().equals(oldOrgs.get(j).getId())) {
                        noChangedOrgs.add(oldOrgs.get(j));
                        break;
                    }
                }
                if (j >= oldOrgs.size()) newOrgs.add(staff.getOrganizations().get(i));
            }
        }
        //新增组织
        if (newOrgs.size() > 0) {
            staff.setOrganizations(newOrgs);
            securityDao.insertStaffOrg(staff);
        }
        //从旧组织中去除未变动的组织从而获取需要删除的组织
        oldOrgs.removeAll(noChangedOrgs);
        //删除组织
        if (oldOrgs.size() > 0) {
            staff.setOrganizations(oldOrgs);
            securityDao.deleteStaffOrg(staff);
        }
    }

    @Override
    public SecStaff findStaffByUserName(SecStaff staff) throws BusinessException {
        //查询用户及用户所处的组织
        SecStaff result = securityDao.findStaffByUsername(staff);
        if (result != null) {
            //查询用户的可用角色
            SecStaff stemp = new SecStaff();
            stemp.setId(result.getId());
            stemp.setStatus(1);
            result.setRoles(securityDao.listRoleByStaff(stemp));
            //查询用户所处组织的可用角色
            for (SecOrganization org : result.getOrganizations()) {
                SecOrganization otemp = new SecOrganization();
                otemp.setId(org.getId());
                otemp.setStatus(1);
                List<SecRole> roles = securityDao.listRoleByOrg(otemp);
                //将用户没有的而组织拥有的角色保存到用户的角色列表中
                for (SecRole role : roles) {
                    int i = 0;
                    for (; i<result.getRoles().size(); ++i) {
                        if (result.getRoles().get(i).getId().equals(role.getId())) break;
                    }
                    if (i == result.getRoles().size()) result.getRoles().add(role);
                }
            }
            return result;
        }
        else throw new BusinessException(SecurityConsts.STAFF_NOT_EXIST);
    }

    @Override
    public List<SecRole> listRoleByReq(SecResource resource) {
        return securityDao.listRoleByReq(resource);
    }

    @Override
    public Map<String, String> findRolesByPkey(SecResource resource) {
        List<SecResource> resources = new ArrayList<>();
        //父key为空的时候则查询父id为0的资源
        if (StringUtils.isBlank(resource.getKey())) {
            resource.setPid(0L);
            resources = securityDao.listRoleByResPid(resource);
        }
        else resources = securityDao.listRoleByResPkey(resource);
        Map<String, String> result = new HashMap<>();
        for (SecResource item : resources) {
            String roles = "";
            for (SecRole role : item.getRoles()) roles += role.getId() + ",";
            if (roles.length() > 0) roles = roles.substring(0, roles.length() - 1);
            result.put(item.getKey(), roles);
        }
        return result;
    }

    @Override
    public SecResource findResourceByReq(SecResource secResource) {
        return securityDao.findResourceByReq(secResource);
    }
}
