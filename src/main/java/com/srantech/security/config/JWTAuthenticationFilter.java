package com.srantech.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

//    injected parameters

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    public JWTAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

//    implemented method from  OncePerRequestFilter class
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authentication");
        final String jwt;
        final String userEmail;

//        checking header is null or not and also checking token starts with Bearer or not
        if(authHeader == null ||!authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

//        extracting token from header
        jwt = authHeader.substring(7);

//        extracting user email from jwt token
        userEmail = jwtService.extractUsername(jwt);

//        checking user is authenticated or  not
        if(userEmail !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

//            checking token is valid or not
            if (jwtService.isTokenValid(jwt, userDetails)) {
//                this class object is created after verifying token and user both are valid
                UsernamePasswordAuthenticationToken authFilter =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());
                authFilter.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
//          updating spring context
                SecurityContextHolder.getContext().setAuthentication(authFilter);
            }
        }
        filterChain.doFilter(request,response);
    }
}
