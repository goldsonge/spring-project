package com.estsoft.springproject.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> firstFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();

        filter.setFilter(new FirstFilter());
        filter.addUrlPatterns("/books");
        filter.setOrder(3);  // doFilter() 순서 보장. init이나 destroy는 순서가 보장되지 않을 수 있음
        return filter;
    }
    @Bean
    public FilterRegistrationBean<Filter> secondFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new SecondFilter());
        filter.addUrlPatterns("/books");
        filter.setOrder(3);  // doFilter() 순서 보장. init이나 destroy는 순서가 보장되지 않을 수 있음
        return filter;
    }
//    @Bean
//    public FilterRegistrationBean<Filter> thirdFilter() {
//        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
//        filter.setFilter(new ThirdFilter());
//        filter.addUrlPatterns("/books");
//        filter.setOrder(3);  // doFilter() 순서 보장. init이나 destroy는 순서가 보장되지 않을 수 있음
//
//        return filter;
//    }
}
