package com.yangche.gatewayservice.controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

public class LoginFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        var headers = request.getHeaderNames();
        var method = request.getMethod();
        headers.asIterator().forEachRemaining(System.out::println);
        System.out.println("executing filter by method=" + method + ": url=" + uri);
        filterChain.doFilter(request, response);
    }
}
