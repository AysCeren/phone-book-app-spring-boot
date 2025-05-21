package com.project.contactsdemo.core.service;

import com.project.contactsdemo.core.config.RateLimiterConfig;
import com.project.contactsdemo.core.exception.RateLimitException;
import com.project.contactsdemo.core.pair.Pair;
import com.project.contactsdemo.core.properties.RateLimitProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class RateLimiterService {
    private final RateLimiterConfig config;
    private final Map<String, Map<String, Pair<Integer, LocalDateTime>>> requestMap = new ConcurrentHashMap<>();

    public RateLimiterService(RateLimiterConfig config) {
        this.config = config;
    }

    private boolean isRateLimited(String identifier, String service) {
        RateLimitProperties limitConfig = config.getLimits().get(service);
        if (limitConfig == null) {
            return false;
        } //this checks whether we have rate limitation or not.
        LocalDateTime currentTime = LocalDateTime.now();
        synchronized (requestMap) {
            // I should check the 'computeIfAbsent'
            Map<String, Pair<Integer, LocalDateTime>> serviceMap = requestMap.computeIfAbsent(identifier, k -> new ConcurrentHashMap<>());
            Pair<Integer, LocalDateTime> requestData = serviceMap.getOrDefault(service, new Pair<>(0, currentTime));
            int requestCount = requestData.getFirst();
            LocalDateTime lastRequestTime = requestData.getSecond();
//
            //Bu kısım çok önemli
            if (lastRequestTime.plusMinutes(limitConfig.getTimeFrameMinutes()).isBefore(currentTime)) {
                serviceMap.put(service, new Pair<>(0, currentTime));
                return false;
            }

            return requestCount >= limitConfig.getRateLimit();
        }
    }

    public void registerRequest(String identifier, String service) {
        RateLimitProperties limitConfig = config.getLimits().get(service);
        if (limitConfig == null) {
            return;
        }

        if (isRateLimited(identifier, service)) { //aşıp aşmama durumunu ayrı bir methodyn içerisine aldım
            throw new RateLimitException("Rate limit has been exceeded", identifier, limitConfig.getRateLimit());
        }

        LocalDateTime currentTime = LocalDateTime.now();
//synchroniezd'a bak
        synchronized (requestMap) {
            Map<String, Pair<Integer, LocalDateTime>> serviceMap = requestMap.computeIfAbsent(identifier, k -> new ConcurrentHashMap<>());
            Pair<Integer, LocalDateTime> requestData = serviceMap.getOrDefault(service, new Pair<>(0, currentTime));
            int requestCount = requestData.getFirst();
            serviceMap.put(service, new Pair<>(requestCount + 1, currentTime));

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    //buna bakalım
                    synchronized (requestMap) {
                        Map<String, Pair<Integer, LocalDateTime>> identifierMap = requestMap.get(identifier);
                        if (identifierMap != null) { // Check for null
                            identifierMap.remove(service);
                            if (identifierMap.isEmpty()) {
                                requestMap.remove(identifier);
                            }
                        }
                    }
                }
            }, TimeUnit.MINUTES.toMillis(limitConfig.getTimeFrameMinutes()));
        }
    }

}

