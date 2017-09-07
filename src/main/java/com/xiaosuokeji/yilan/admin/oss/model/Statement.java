package com.xiaosuokeji.yilan.admin.oss.model;

import java.util.List;

/**
 * Created by gustinlau on 2017/8/15.
 */
public class Statement {
    private String Effect;
    private List<String> Action;
    private List<String> Resource;

    public String getEffect() {
        return Effect;
    }

    public void setEffect(String Effect) {
        this.Effect = Effect;
    }

    public List<String> getAction() {
        return Action;
    }

    public void setAction(List<String> Action) {
        this.Action = Action;
    }

    public List<String> getResource() {
        return Resource;
    }

    public void setResource(List<String> Resource) {
        this.Resource = Resource;
    }
}
