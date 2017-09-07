package com.xiaosuokeji.yilan.server.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 系统属性<br/>
 * Created by xuxiaowei on 2017/8/13.
 */
public class SystemProperty extends BaseModel {

    private Long id;

    @Length(max = 20, message = "网站名称长度至多20个字符", groups = Save.class)
    private String webName;

    @Size(max = 5, message = "小程序二维码至多5张", groups = Save.class)
    private List<String> miniAppQrcodeList;

    @JsonIgnore
    private String miniAppQrcodes;

    @Size(max = 5, message = "小程序至多5个", groups = Save.class)
    private List<String> miniAppNameList;

    @JsonIgnore
    private String miniAppNames;

    @Length(max = 200, message = "邮箱长度至多200个字符", groups = Save.class)
    @Email(message = "请输入正确的邮箱", groups = Save.class)
    private String email;

    @Length(max = 200, message = "地址长度至多200个字符", groups = Save.class)
    private String address;

    @Length(max = 20, message = "电话长度至多20个字符", groups = Save.class)
    private String phone;

    @Length(max = 20, message = "qq长度至多20个字符", groups = Save.class)
    private String qq;

    @Length(max = 50, message = "版权长度至多50个字符", groups = Save.class)
    private String copyright;

    @Length(max = 50, message = "备案证编号长度至多50个字符", groups = Save.class)
    private String recordNumber;

    @Length(max = 50, message = "许可证长度至多50个字符", groups = Save.class)
    private String license;

    @Length(max = 200, message = "关于我们长度至多200个字符", groups = Save.class)
    private String aboutUs;

    @Size(max = 3, message = "合作伙伴至多3个", groups = Save.class)
    private List<String> partnerList;

    @JsonIgnore
    private String partners;

    @Size(max = 3, message = "合作伙伴链接至多3个", groups = Save.class)
    private List<String> partnerUrlList;

    @JsonIgnore
    private String partnerUrls;

    @Length(max = 200, message = "二维码长度至多200个字符", groups = Save.class)
    private String qrCode;

    public interface Save {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public List<String> getMiniAppQrcodeList() {
        return miniAppQrcodeList;
    }

    public void setMiniAppQrcodeList(List<String> miniAppQrcodeList) {
        this.miniAppQrcodeList = miniAppQrcodeList;
    }

    public String getMiniAppQrcodes() {
        return miniAppQrcodes;
    }

    public void setMiniAppQrcodes(String miniAppQrcodes) {
        this.miniAppQrcodes = miniAppQrcodes;
    }

    public List<String> getMiniAppNameList() {
        return miniAppNameList;
    }

    public void setMiniAppNameList(List<String> miniAppNameList) {
        this.miniAppNameList = miniAppNameList;
    }

    public String getMiniAppNames() {
        return miniAppNames;
    }

    public void setMiniAppNames(String miniAppNames) {
        this.miniAppNames = miniAppNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public List<String> getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(List<String> partnerList) {
        this.partnerList = partnerList;
    }

    public String getPartners() {
        return partners;
    }

    public void setPartners(String partners) {
        this.partners = partners;
    }

    public List<String> getPartnerUrlList() {
        return partnerUrlList;
    }

    public void setPartnerUrlList(List<String> partnerUrlList) {
        this.partnerUrlList = partnerUrlList;
    }

    public String getPartnerUrls() {
        return partnerUrls;
    }

    public void setPartnerUrls(String partnerUrls) {
        this.partnerUrls = partnerUrls;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
