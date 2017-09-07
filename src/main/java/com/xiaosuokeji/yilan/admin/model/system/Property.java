package com.xiaosuokeji.yilan.admin.model.system;

import com.xiaosuokeji.yilan.admin.model.base.PaginationModel;

import java.util.List;

/**
 * Created by Aranl_lin on 2017/8/17.
 */
public class Property extends PaginationModel {


    /**
     * createTime : 2017-08-13 19:54:27
     * updateTime : 2017-08-14 16:12:21
     * id : 1
     * webName : 医览网
     * miniAppQrcodeList : ["http://www.image.com"]
     * miniAppNameList : ["小程序"]
     * email : http://www.xiaosuokeji.com
     * address : 小梭信息科技有限公司
     * phone : 020-123456
     * qq : 12346789@qq.com
     * copyright : 版权
     * recordNumber : 备案号
     * license : 许可证
     * aboutUs : http://www.aboutUs.com
     * partnerList : ["小梭信息"]
     * partnerUrlList : ["http://www.xiaosuokeji.com"]
     */

    private String createTime;
    private String updateTime;
    private Long id;
    private String webName;
    private String email;
    private String address;
    private String phone;
    private String qq;
    private String copyright;
    private String recordNumber;
    private String license;
    private String aboutUs;
    private String qrCode;
    private List<String> miniAppQrcodeList;
    private List<String> miniAppNameList;
    private List<String> partnerList;
    private List<String> partnerUrlList;

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

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

    public List<String> getMiniAppQrcodeList() {
        return miniAppQrcodeList;
    }

    public void setMiniAppQrcodeList(List<String> miniAppQrcodeList) {
        this.miniAppQrcodeList = miniAppQrcodeList;
    }

    public List<String> getMiniAppNameList() {
        return miniAppNameList;
    }

    public void setMiniAppNameList(List<String> miniAppNameList) {
        this.miniAppNameList = miniAppNameList;
    }

    public List<String> getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(List<String> partnerList) {
        this.partnerList = partnerList;
    }

    public List<String> getPartnerUrlList() {
        return partnerUrlList;
    }

    public void setPartnerUrlList(List<String> partnerUrlList) {
        this.partnerUrlList = partnerUrlList;
    }
}
