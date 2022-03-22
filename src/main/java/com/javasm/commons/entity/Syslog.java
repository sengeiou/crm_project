package com.javasm.commons.entity;

public class Syslog {
    private String id;
    private String opsUser;
    private String opsTime;
    private String modelName;
    private String opsName;
    private Boolean opsResult;

    @Override
    public String toString() {
        return "Syslog{" +
                "id='" + id + '\'' +
                ", opsUser='" + opsUser + '\'' +
                ", opsTime='" + opsTime + '\'' +
                ", modelName='" + modelName + '\'' +
                ", opsName='" + opsName + '\'' +
                ", opsResult=" + opsResult +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpsUser() {
        return opsUser;
    }

    public void setOpsUser(String opsUser) {
        this.opsUser = opsUser;
    }

    public String getOpsTime() {
        return opsTime;
    }

    public void setOpsTime(String opsTime) {
        this.opsTime = opsTime;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getOpsName() {
        return opsName;
    }

    public void setOpsName(String opsName) {
        this.opsName = opsName;
    }

    public Boolean getOpsResult() {
        return opsResult;
    }

    public void setOpsResult(Boolean opsResult) {
        this.opsResult = opsResult;
    }
}
