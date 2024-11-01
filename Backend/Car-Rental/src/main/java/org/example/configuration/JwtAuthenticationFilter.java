package org.example.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.service.jwt.UserService;
import org.example.util.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserService userService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader=request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if(StringUtils.isEmpty(authHeader)|| StringUtils.startsWith(authHeader,"Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt=authHeader.substring(7);
        userEmail=jwtUtil.getUserName(jwt);
        if(StringUtils.isNotEmpty(userEmail)
                && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userService.userDetailsService().loadUserByUsername(userEmail);
            if(jwtUtil.isTokenValid(jwt,userDetails)){
                SecurityContext securityContext=SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(securityContext);
            }
        }
    }
}
