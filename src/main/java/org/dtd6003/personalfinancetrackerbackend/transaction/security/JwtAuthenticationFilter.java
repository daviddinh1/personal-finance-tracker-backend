package org.dtd6003.personalfinancetrackerbackend.transaction.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dtd6003.personalfinancetrackerbackend.auth.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            logger.debug("this is the header " + header);

            try{
                if(jwtTokenProvider.validateToken(token)){
                    Long userId = jwtTokenProvider.getUserIdFromJWT(token);
                    logger.debug("Authenticated userId: {}" + userId);

                    //build auth object to store in security context
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    userId,
                                    null,
                                    Collections.emptyList()
                            );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }else{
                    logger.debug("Validating the token has failed");
                }
            } catch(Exception ex){
                logger.debug("No bearer token");

                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}
