//
//package com.project.contactsdemo.filtering;
//
//import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequestWrapper;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Enumeration;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Component
//@RequiredArgsConstructor
//public class MyCustomHeaderFilter extends OncePerRequestFilter implements Ordered {
//
//    public final ApplicationContext applicationContext;
//    @Override
//    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request) {
//
//            final String myCustomHeaderName = "Custom-Header";
//            final String myCustomHeaderValue = "FooBar";
//
//            @Override
//            public Enumeration<String> getHeaderNames() {
//                List<String> headerNames = Collections.list(super.getHeaderNames());
//                headerNames.add(myCustomHeaderName);
//                return Collections.enumeration(headerNames);
//            }
//
//            @Override
//            public Enumeration<String> getHeaders(String name) {
//                if (name.equals(myCustomHeaderName)) {
//                    return Collections.enumeration(Collections.singletonList(myCustomHeaderValue));
//                }
//                return super.getHeaders(name);
//            }
//
//            @Override
//            public String getHeader(String name) {
//                if (name.equals(myCustomHeaderName)) {
//                    return myCustomHeaderValue;
//                }
//                return super.getHeader(name);
//            }
//        };
//
//        chain.doFilter(wrappedRequest, response);
//    }
//
//    @Override
//    public int getOrder() {
//       if(applicationContext.containsBean("requestLoggingFilter")) {
//           return -1; //go up before requestLogging filter
//       }else
//           return LOWEST_PRECEDENCE; //Otherwise, go dowb after other filters
//    }
////
////    @Override
////    public int getOrder() {
////        if(applicationContext.containsBean("RequestLoggingFilter")) {
////            return -1;
////        }
////        return LOWEST_PRECEDENCE;
////    }
//}
