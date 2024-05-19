package com.yangche.gatewayservice.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import org.springframework.web.filter.OncePerRequestFilter;

public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        var uri = req.getRequestURI();
        if (uri.equals("/user/login")) {
            var userAgent = req.getHeader("User-Agent");
            var now = new Date();
            System.out.println("user logined at: " + now + " by " + userAgent);
        }
        filterChain.doFilter(req, res);
    }
}
