package com.hotel.management.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hotel.management.JwtUtils.JwtUtils;
import com.hotel.management.Service.JwtService;
import org.apache.commons.lang3.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final String authHeader=request.getHeader("Authorization");
		final String jwt;
		final String username;
		
		if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader,"Bearer "))
		{
			filterChain.doFilter(request, response);
			return;
		}
		jwt=authHeader.substring(7);
		username=jwtUtils.extractUsername(jwt);
		if(StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails=jwtService.userDetailsService().loadUserByUsername(username);
			if(jwtUtils.IsTokenvalid(jwt, userDetails))
			{
				SecurityContext context=SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				context.setAuthentication(authToken);
				SecurityContextHolder.setContext(context);
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
	
}
