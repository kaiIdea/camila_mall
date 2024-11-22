package com.camila.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "camila.auth")
public class AuthProperties {
    private List<String> includePaths;
    private List<String> excludePaths;
}
