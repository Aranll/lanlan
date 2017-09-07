package com.xiaosuokeji.yilan.admin.model.base;

/**
 * Created by GustinLau on 2017-05-01.
 */
public abstract class PaginationModel extends BaseModel {

    private long page = 1;         //分页输出参数
    private long offset = 0;       //游标初始值(偏移值)
    private long limit = 10;       //显示条数
    private String sort;           //排序字段
    private String order;          //排序方式

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        page = page > 0 ? page : 1;
        this.page = page;
        this.offset = (this.page - 1) * this.limit;
    }

    public long getOffset() {
        return offset;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit > 0 ? limit : this.limit;
        this.offset = (this.page - 1) * this.limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
