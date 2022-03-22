package com.javasm.commons.config;


import org.springframework.stereotype.Component;

@Component
public class AdminTokenInfo {
    private Long expTime;
    private String key;

    public Long getExpTime() {
        return expTime;
    }

    public void setExpTime(Long expTime) {
        this.expTime = expTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
