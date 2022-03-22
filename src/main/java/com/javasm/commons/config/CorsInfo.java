package com.javasm.commons.config;


import java.util.List;


public class CorsInfo {
    private String path;
    private Long maxAge;
    private List<String> origins;
    private List<String> allowedHeaders;
    private List<String> allowedMethods;
    private Boolean credentials;
    private List<String> exposedHeader;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }

    public List<String> getOrigins() {
        return origins;
    }

    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }

    public List<String> getAllowedHeaders() {
        return allowedHeaders;
    }

    public void setAllowedHeaders(List<String> allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    public List<String> getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(List<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public Boolean getCredentials() {
        return credentials;
    }

    public void setCredentials(Boolean credentials) {
        this.credentials = credentials;
    }

    public List<String> getExposedHeader() {
        return exposedHeader;
    }

    public void setExposedHeader(List<String> exposedHeader) {
        this.exposedHeader = exposedHeader;
    }
}
