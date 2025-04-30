//package com.project.contactsdemo.loggingfilter;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//@Component
//public class LoggingInterceptor implements HandlerInterceptor {
//
//    //Logger for this class
//    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        logger.info("Request: {} {}", request.getMethod(), request.getRequestURI());
//        return true; //Allows the request to proceed
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        logger.info("Respone {} {}", response.getStatus(), request.getRequestURI());
//        if(ex != null) {
//            //Logs any exception
//            logger.error("Exception: ", ex);
//        }
//        //void
//    }
//}
