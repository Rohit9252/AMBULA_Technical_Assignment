package com.userLocation.jwt;

import com.userLocation.service.UserModelServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class AuthTokenFilter  extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserModelServiceImpl userModelServiceImpl;


    /**
     * Parses the token from the Authorization header.
     *
     * @param request the HTTP servlet request
     * @return the parsed token
     */
    private String parseToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");


        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7, header.length());
        }


        return null;
    }




    /**
     * Performs the filtering of the HTTP request.
     *
     * @param request     the HTTP servlet request
     * @param response    the HTTP servlet response
     * @param filterChain the filter chain
     * @throws ServletException if a servlet-related exception occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        try{
            String token = parseToken(request);
            if(token !=null && jwtUtil.verifyToken(token)){
                String username  = jwtUtil.getUserNameFromToken(token);
                UserDetails userDetails =  userModelServiceImpl.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken
                        authenticationToken =  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);


            }



        }catch (Exception e){
            System.out.println("Cannot set user authentication: {}"+ e);
        }

        filterChain.doFilter(request, response);



    }
}
