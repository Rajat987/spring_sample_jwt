package com.test.Sample.utility;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.test.Sample.Service.UserDetailsService;

@Component
public class CustomFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String userName = null;
		String token = null;
		UserDetails userDeatails = null;
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer")) {
			token = authHeader.split(" ")[1];
			userName = jwtUtil.extractUserName(token);
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			userDeatails = service.loadUserByUsername(userName);
			if (jwtUtil.validateToken(token, userDeatails)) {
				UsernamePasswordAuthenticationToken uPAT = new UsernamePasswordAuthenticationToken(userDeatails, null,
						userDeatails.getAuthorities());
				uPAT.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(uPAT);
			}
		}

		filterChain.doFilter(request, response);

	}

}
