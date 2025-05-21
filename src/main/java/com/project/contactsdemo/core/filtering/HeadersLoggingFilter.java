package com.project.contactsdemo.core.filtering;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
@Slf4j
public class HeadersLoggingFilter implements Filter {

    //init is called when web container puts the filter in service
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Collections.list(httpRequest.getHeaderNames())
                .forEach(header ->
                        log.info("Header {}={}, IP = {}", header, servletRequest.getLocalAddr(),
                                header, httpRequest.getHeader(header)));
        filterChain.doFilter(httpRequest, servletResponse); //this is
    }
    //and destroy is called when web container takes this filter out of the service
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

