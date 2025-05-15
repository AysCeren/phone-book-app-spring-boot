package com.project.contactsdemo.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
public class RateLimitProperties {
    private int rateLimit = 0;
    private Long timeFrameMinutes;
}
