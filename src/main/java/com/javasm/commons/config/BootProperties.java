package com.javasm.commons.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "crm")
public class BootProperties {

    private CorsInfo cors;

    private AdminTokenInfo tokenInfo;

    public CorsInfo getCors() {
        return cors;
    }

    public void setCors(CorsInfo cors) {
        this.cors = cors;
    }

    public AdminTokenInfo getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(AdminTokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
}
