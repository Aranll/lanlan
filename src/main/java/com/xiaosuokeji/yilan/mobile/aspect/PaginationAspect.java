package com.xiaosuokeji.yilan.mobile.aspect;

import com.xiaosuokeji.yilan.mobile.model.base.PaginationModel;
import com.xiaosuokeji.yilan.mobile.util.GsonUtils;
import com.xiaosuokeji.yilan.mobile.util.WebUtils;
import com.xiaosuokeji.yilan.mobile.annotation.Pagination;
import com.xiaosuokeji.yilan.mobile.constant.PaginationConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by GustinLau on 2017-04-07.
 */
@Component("mobilePaginationAspect")
@Aspect
public class PaginationAspect {

    @Pointcut("execution(* com.xiaosuokeji.yilan.mobile.controller.*.*(..))||execution(* com.xiaosuokeji.yilan.mobile.controller.*.*.*(..))")
    public void aspect() {
    }

    @Around(value = "aspect()&&@annotation(pagination)")
    public Object pagination(ProceedingJoinPoint pjp, Pagination pagination) throws Throwable {

        //获取参数
        HttpServletRequest request = null;
        Model model = null;
        Object[] args = pjp.getArgs();
        PaginationModel paginationModel = null;
        for (Object arg : args) {
            if (arg instanceof Model) {
                model = (Model) arg;
                continue;
            }
            if (arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
                continue;
            }
            if (arg instanceof PaginationModel) {
                paginationModel = (PaginationModel) arg;
            }
        }
        //参数检测
        if (model == null) {
            throw new Exception("方法使用了@Pagination注解却没有引入Model参数");
        }
        if (request == null) {
            throw new Exception("方法使用了@Pagination注解却没有引入HttpServletRequest参数");
        }
        if (paginationModel == null) {
            throw new Exception("方法使用了@Pagination注解却没有引入PaginationParams类型参数");
        }
        Object result = pjp.proceed();

        //获取分页值
        Long page, limit;

        page = paginationModel.getPage();

        limit = paginationModel.getLimit();

        //向接口发起请求
        JSONObject response;

        response = WebUtils.doRawRequest(pagination.api(), paginationModel);
        //检测返回数据
        if (!response.getBoolean("status")) {
            throw new Exception(response.getString("msg"));
        }

        JSONObject data = response.getJSONObject("data");

        //分页总数
        float total;
        long maxPage;
        total = Float.parseFloat(data.get("total").toString());
        maxPage = (int) Math.ceil(total / limit);

        //当页数超出时需要再次请求
        if (maxPage > 0 && page > maxPage) {
            paginationModel.setPage(maxPage);

            //向接口发起请求
            response = WebUtils.doRawRequest(pagination.api(), paginationModel);
            data = response.getJSONObject("data");
            //分页总数
            total = Float.parseFloat(data.get("total").toString());
            maxPage = (int) Math.ceil(total / limit);
        }

        model.addAttribute(pagination.items() + "_total", (int) total);
        JSONArray list = data.getJSONArray("list");
        List resultList = new ArrayList();
        for (Object obj : list) {
            resultList.add(GsonUtils.objectFromJson(obj.toString(),pagination.itemClass()));
        }
        model.addAttribute(pagination.items(), resultList);
        model.addAttribute("listTotal",data.get("total"));
        //确定分页
        if (maxPage > 1) {
            model.addAttribute("isPagination", true);
            String url = request.getRequestURL().toString() + "?";

            String queryString = parmasMap2String(request);

            String first;
            if (page <= 1) {
                first = "<li class=\"disabled\"><a title=\"" + PaginationConstants.FIRST + "\"><i class=\"fa fa-angle-double-left\"></i></a></li>" +
                        "<li class=\"disabled\"><a title=\"" + PaginationConstants.BEFORE + "\"><i class=\"fa fa-angle-left\"></i></a></li>";
            } else {
                first = "<li><a href=\"" + url + PaginationConstants.PAGE + "=1" + queryString + "\"><i class=\"fa fa-angle-double-left\" title=\"" + PaginationConstants.FIRST + "\"></i></a></li>" +
                        "<li><a href=\"" + url + PaginationConstants.PAGE + "=" + String.valueOf(page - 1) + queryString + "\"  title=\"" + PaginationConstants.BEFORE + "\"><i class=\"fa fa-angle-left\"></i></a></li>";
            }

            String last;
            if (page >= maxPage) {
                last = "<li class=\"disabled\"><a title=\"" + PaginationConstants.NEXT + "\"><i class=\"fa fa-angle-right\"></i></a></li>" +
                        "<li class=\"disabled\"><a title=\"" + PaginationConstants.LAST + "\"><i class=\"fa fa-angle-double-right\"></i></a></li>";
            } else {
                last = "<li><a href=\"" + url + PaginationConstants.PAGE + "=" + String.valueOf(page + 1) + queryString + "\" title=\"" + PaginationConstants.NEXT + "\"><i class=\"fa fa-angle-right\"></i></a></li>" +
                        "<li><a href=\"" + url + PaginationConstants.PAGE + "=" + maxPage + queryString + "\" title=\"" + PaginationConstants.LAST + "\"><i class=\"fa fa-angle-double-right\"></i></a></li>";
            }

            StringBuilder center = new StringBuilder();
            if (maxPage <= PaginationConstants.SHOW_PER_PAGE) {
                for (long i = 1; i <= maxPage; i++) {
                    if (i == page)
                        center.append("<li class=\"active\" ><a>").append(String.valueOf(i)).append("</a></li>");
                    else
                        center.append("<li><a href=\"").append(url).append(PaginationConstants.PAGE).append("=").append(String.valueOf(i)).append(queryString).append("\">").append(String.valueOf(i)).append("</a></li>");
                }
            } else {
                if (page >= PaginationConstants.SHOW_PER_PAGE && (maxPage - page) >= PaginationConstants.SHOW_PER_PAGE) {
                    center = new StringBuilder("<li><a>...</a></li>");
                    for (long i = page - PaginationConstants.PAGE_SIDE; i <= page + PaginationConstants.PAGE_SIDE; i++) {
                        if (i == page)
                            center.append("<li class=\"active\" ><a title=\"第").append(i).append("页\">").append(String.valueOf(i)).append("</a></li>");
                        else
                            center.append("<li><a href=\"").append(url).append(PaginationConstants.PAGE).append("=").append(String.valueOf(i)).append(queryString).append("\" title=\"第").append(i).append("页\">").append(String.valueOf(i)).append("</a></li>");
                    }
                    center.append("<li><a>...</a></li>");
                } else if (page >= PaginationConstants.SHOW_PER_PAGE) {
                    center = new StringBuilder("<li><a>...</a></li>");
                    for (long i = maxPage - PaginationConstants.SHOW_PER_PAGE; i <= maxPage; i++) {
                        if (i == page)
                            center.append("<li class=\"active\" ><a title=\"第").append(i).append("页\">").append(String.valueOf(i)).append("</a></li>");
                        else
                            center.append("<li><a href=\"").append(url).append(PaginationConstants.PAGE).append("=").append(String.valueOf(i)).append(queryString).append("\" title=\"第").append(i).append("页\">").append(String.valueOf(i)).append("</a></li>");
                    }
                } else {
                    for (int i = 1; i <= PaginationConstants.SHOW_PER_PAGE; i++) {
                        if (i == page)
                            center.append("<li class=\"active\" ><a title=\"第").append(i).append("页\">").append(String.valueOf(i)).append("</a></li>");
                        else
                            center.append("<li><a href=\"").append(url).append(PaginationConstants.PAGE).append("=").append(String.valueOf(i)).append(queryString).append("\" title=\"第").append(i).append("页\">").append(String.valueOf(i)).append("</a></li>");
                    }
                    center.append("<li><a>...</a></li>");
                }
            }
            model.addAttribute("pagination", first + center + last);
        } else {
            model.addAttribute("isPagination", false);
        }

        return result;
    }

    private String parmasMap2String(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getKey().equals(PaginationConstants.PAGE))
                continue;
            str.append("&").append(entry.getKey()).append("=").append(entry.getValue()[0]);
        }
        return "" + str;
    }
}
