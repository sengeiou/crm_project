package com.javasm.sys.entity;



public class GetDate {
    private String frontTime;
    private String afterTime;


    @Override
    public String toString() {
        return "GetDate{" +
                "frontTime='" + frontTime + '\'' +
                ", afterTime='" + afterTime + '\'' +
                '}';
    }

    public String getFrontTime() {
        return frontTime;
    }

    public void setFrontTime(String frontTime) {
        this.frontTime = frontTime;
    }

    public String getAfterTime() {
        return afterTime;
    }

    public void setAfterTime(String afterTime) {
        this.afterTime = afterTime;
    }
}
