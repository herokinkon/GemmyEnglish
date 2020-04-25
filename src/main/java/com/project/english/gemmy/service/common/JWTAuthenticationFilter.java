package com.project.english.gemmy.service.common;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.english.gemmy.model.dto.LoginFormDTO;
import com.project.english.gemmy.util.CommonConstant;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = null, password = null;

		try {
			LoginFormDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginFormDTO.class);
			username = loginDTO.getUsername();
			password = loginDTO.getPassword();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		response.addHeader(CommonConstant.JWT_HEADER, "admin");
	}
}
