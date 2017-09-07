package com.xiaosuokeji.yilan.admin.oss.model;

import java.util.List;

/**
 * Created by gustinlau on 2017/8/15.
 */
public class Policy {
    private String Version;
    private List<Statement> Statement;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public List<Statement> getStatement() {
        return Statement;
    }

    public void setStatement(List<Statement> Statement) {
        this.Statement = Statement;
    }

}
