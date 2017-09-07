package com.xiaosuokeji.yilan.admin.model.resource;

import com.xiaosuokeji.yilan.admin.model.base.Resource;
import org.springframework.security.access.method.P;

import java.util.List;

/**
 * Created by Aranl_lin on 2017/8/10.
 */
public class PublicNumber extends Resource{

    private String qrCode;
    private String developer;
    private List<PublicNumber> publicNumbers;

    public List<PublicNumber> getPublicNumbers() {
        return publicNumbers;
    }

    public void setPublicNumbers(List<PublicNumber> publicNumbers) {
        this.publicNumbers = publicNumbers;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}

