package com.xiaosuokeji.yilan.admin.model.system;

import com.xiaosuokeji.yilan.admin.model.base.PaginationModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Aranl_lin on 2017/8/22.
 */
public class Goods extends PaginationModel {


        /**
         * createTime : 2017-08-06 20:17:40
         * updateTime : 2017-08-06 20:17:40
         * id : 3
         * name : 60天
         * price : 15
         * duration : 60
         * status : 1
         * statusDesc : 上架
         * desc : 60天会员身份
         * seq : 0
         */


        private Long id;
        private String name;
        private BigDecimal price;
        private Integer duration;
        private Integer status;
        private String statusDesc;
        private String desc;
        private Long seq;
        private String createTime;
        private String updateTime;

        private Map<String,String> dynamic;

        public Map<String, String> getDynamic() {
            return dynamic;
        }

        public void setDynamic(Map<String, String> dynamic) {
            this.dynamic = dynamic;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getStatusDesc() {
            return statusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            this.statusDesc = statusDesc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Long getSeq() {
            return seq;
        }

        public void setSeq(Long seq) {
            this.seq = seq;
        }
}
