package com.project.contactsdemo.core.aspect;

import com.project.contactsdemo.core.config.RateLimiterConfig;
import com.project.contactsdemo.core.ratelimitedannotation.RateLimited;
import com.project.contactsdemo.core.service.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimiterOrientedAspect {

    private final RateLimiterService rateLimiterService;
    private final RateLimiterConfig config;

    @Around("@annotation(rateLimited)")
    public Object enforceRateLimit(ProceedingJoinPoint joinPoint, RateLimited rateLimited) throws Throwable {
        if (!config.getEnabled()) {
            return joinPoint.proceed(); // Skip if disabled
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = request != null ? request.getRemoteAddr() : "UNKNOWN";

        rateLimiterService.registerRequest(ipAddress, rateLimited.service());

        return joinPoint.proceed();
    }
}
