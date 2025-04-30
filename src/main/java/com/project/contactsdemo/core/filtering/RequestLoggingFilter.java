//package com.project.contactsdemo.filtering;
//
//import com.project.contactsdemo.logging.CachedHttpServletRequest;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.catalina.core.ApplicationFilterChain;
//import org.apache.commons.io.IOUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//@Order(0)
//@Component
//@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
//public class RequestLoggingFilter extends OncePerRequestFilter {
//
//    private final static Logger LOGGER = LoggerFactory.getLogger(RequestLoggingFilter.class);
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        CachedHttpServletRequest cachedHttpServletRequest = new CachedHttpServletRequest(request);
//        filterChain.doFilter(cachedHttpServletRequest, response);
//        LOGGER.info("REQUEST DATA: " + IOUtils.toString(cachedHttpServletRequest.getInputStream(), StandardCharsets.UTF_8));
//
//        //Response logla
//        //filter'ları araştır
//    }
//}