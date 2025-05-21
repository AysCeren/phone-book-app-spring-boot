//package com.project.contactsdemo.core.filtering;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//@Order(1)
//public class RateLimitingFilter implements Filter {
//    // Map to store request counts per IP address
//    private final Map<String, AtomicInteger> requestCountsPerIpAddress = new ConcurrentHashMap<>();
//    // Maximum requests allowed per minute
//    private static final int MAX_REQUESTS_PER_MINUTE = 5;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Optional: Initialization logic, if needed
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//
//        String clientIpAddress = httpServletRequest.getRemoteAddr(); //take the ip
//        requestCountsPerIpAddress.putIfAbsent(clientIpAddress, new AtomicInteger(0));
//        AtomicInteger requestCount = requestCountsPerIpAddress.get(clientIpAddress);
//        //take the request count of current Ip address and increment it
//        requestCount.incrementAndGet();
//        if (requestCount.intValue() >= MAX_REQUESTS_PER_MINUTE) {
//            httpServletResponse.setStatus(HttpServletResponse.SC_TOO_MANY_REQUESTS);
//            httpServletResponse.getWriter().write("Too many requests. Please try again later.");
//        }
//        chain.doFilter(request, response);
//
//        //how to reset the period?
//    }
//    @Override
//    public void destroy() {
//        // Optional: Cleanup resources, if needed
//    }
//}
