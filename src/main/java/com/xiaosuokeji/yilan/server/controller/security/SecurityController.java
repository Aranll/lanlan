package com.xiaosuokeji.yilan.server.controller.security;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.model.security.SecOrganization;
import com.xiaosuokeji.yilan.server.model.security.SecResource;
import com.xiaosuokeji.yilan.server.model.security.SecRole;
import com.xiaosuokeji.yilan.server.model.security.SecStaff;
import com.xiaosuokeji.yilan.server.service.intf.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 系统安全Controller<br/>
 * Created by xuxiaowei on 2017/5/22.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/admin/v1/security/resource/create", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult createResource(@Validated(SecResource.Create.class) @RequestBody SecResource resource) throws BusinessException {
        securityService.createResource(resource);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/resource/delete", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult deleteResource(@RequestBody SecResource resource) throws BusinessException {
        securityService.deleteResource(resource);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/resource/find", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult findResource(@RequestBody SecResource resource) {
        return ServiceResult.build().data(securityService.findResource(resource));
    }

    @RequestMapping(value = "/admin/v1/security/resource/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateResource(@Validated(SecResource.Update.class) @RequestBody SecResource resource) throws BusinessException {
        securityService.updateResource(resource);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/resource/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listResource(@RequestBody SecResource resource) {
        return ServiceResult.build().data(securityService.listResourceAndCount(resource));
    }

    @RequestMapping(value = "/admin/v1/security/resource/tree", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listResourceTree(@RequestBody SecResource resource) {
        return ServiceResult.build().data(securityService.listResourceTree(resource));
    }

    @RequestMapping(value = "/admin/v1/security/role/create", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult createRole(@Validated(SecRole.Create.class) @RequestBody SecRole role) throws BusinessException {
        securityService.createRole(role);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/role/delete", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult deleteRole(@RequestBody SecRole role) throws BusinessException {
        securityService.deleteRole(role);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/role/find", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult findRole(@RequestBody SecRole role) {
        return ServiceResult.build().data(securityService.findRole(role));
    }

    @RequestMapping(value = "/admin/v1/security/role/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateRole(@Validated(SecRole.Update.class) @RequestBody SecRole role) throws BusinessException {
        securityService.updateRole(role);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/role/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listRole(@RequestBody SecRole role) {
        return ServiceResult.build().data(securityService.listRoleAndCount(role));
    }

    @RequestMapping(value = "/admin/v1/security/role/pair", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listResourcePair(@RequestBody SecRole role) {
        return ServiceResult.build().data(securityService.listRolePair(role));
    }

    @RequestMapping(value = "/admin/v1/security/role/res/tree", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listResourceTreeByRole(@RequestBody SecRole role) {
        return ServiceResult.build().data(securityService.listResourceTreeByRole(role));
    }

    @RequestMapping(value = "/admin/v1/security/role/res/authorize", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult authorizeResToRole(@RequestBody SecRole role) {
        securityService.authorizeResourceToRole(role);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/staff/create", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult createStaff(@Validated(SecStaff.Create.class) @RequestBody SecStaff staff) throws BusinessException {
        securityService.createStaff(staff);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/staff/delete", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult deleteStaff(@RequestBody SecStaff staff) throws BusinessException {
        securityService.deleteStaff(staff);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/staff/find", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult findStaff(@RequestBody SecStaff staff) {
        //禁止查询超级管理员角色
        staff.setSuperior(0);
        return ServiceResult.build().data(securityService.findStaff(staff));
    }

    @RequestMapping(value = "/admin/v1/security/staff/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateStaff(@Validated(SecStaff.Update.class) @RequestBody SecStaff staff) throws BusinessException {
        securityService.updateStaff(staff);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/staff/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listStaff(@RequestBody SecStaff staff) {
        return ServiceResult.build().data(securityService.listStaffAndCount(staff));
    }

    @RequestMapping(value = "/admin/v1/security/staff/role/pair", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listRolePairByStaff(@RequestBody SecStaff staff) {
        return ServiceResult.build().data(securityService.listRolePairByStaff(staff));
    }

    @RequestMapping(value = "/admin/v1/security/staff/role/authorize", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult authorizeRoleToStaff(@RequestBody SecStaff staff) {
        securityService.authorizeRoleToStaff(staff);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/organization/create", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult createOrganization(@Validated(SecOrganization.Create.class) @RequestBody SecOrganization organization) throws BusinessException {
        securityService.createOrganization(organization);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/organization/delete", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult deleteOrganization(@RequestBody SecOrganization organization) throws BusinessException {
        securityService.deleteOrganization(organization);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/organization/find", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult findOrganization(@RequestBody SecOrganization organization) {
        return ServiceResult.build().data(securityService.findOrganization(organization));
    }

    @RequestMapping(value = "/admin/v1/security/organization/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateOrganization(@Validated(SecOrganization.Update.class) @RequestBody SecOrganization organization) throws BusinessException {
        securityService.updateOrganization(organization);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/organization/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listOrganization(@RequestBody SecOrganization organization) {
        return ServiceResult.build().data(securityService.listOrganizationAndCount(organization));
    }

    @RequestMapping(value = "/admin/v1/security/organization/tree", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listOrgTree(@RequestBody SecOrganization organization) {
        return ServiceResult.build().data(securityService.listOrgTree(organization));
    }

    @RequestMapping(value = "/admin/v1/security/organization/role/pair", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listRolePairByOrganization(@RequestBody SecOrganization organization) {
        return ServiceResult.build().data(securityService.listRolePairByOrganization(organization));
    }

    @RequestMapping(value = "/admin/v1/security/organization/role/authorize", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult authorizeRoleToOrganization(@RequestBody SecOrganization organization) {
        securityService.authorizeRoleToOrganization(organization);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/staff/org/tree", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listOrgTreeByStaff(@RequestBody SecStaff staff) {
        return ServiceResult.build().data(securityService.listOrgTreeByStaff(staff));
    }

    @RequestMapping(value = "/admin/v1/security/staff/org/join", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult joinOrg(@RequestBody SecStaff staff) {
        securityService.joinOrg(staff);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/security/login", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult findStaffByUsername(@RequestBody SecStaff staff) throws BusinessException {
        return ServiceResult.build().data(securityService.findStaffByUserName(staff));
    }

    @RequestMapping(value = "/admin/v1/security/roleByReq", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listRoleByReq(@RequestBody SecResource resource) {
        return ServiceResult.build().data(securityService.listRoleByReq(resource));
    }

    @RequestMapping(value = "/admin/v1/security/roleByRes", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult findRolesByPkey(@RequestBody SecResource resource) {
        return ServiceResult.build().data(securityService.findRolesByPkey(resource));
    }
}
