package br.pokemon.seguranca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.pokemon.dto.CredenciaisContaDto;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	private JWTUtil jwtUtil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationCredentialsNotFoundException {
		try {
			CredenciaisContaDto credenciais = new ObjectMapper()
					.readValue(request.getInputStream(), CredenciaisContaDto.class);
			
			 UsernamePasswordAuthenticationToken token = new  UsernamePasswordAuthenticationToken(credenciais.getApelido(), credenciais.getSenha(), new ArrayList<>());
			 
			 return authenticationManager.authenticate(token);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch(AuthenticationException e1) {
			response.setStatus(401);
			response.setContentType("application/json");
			try {
				response.getWriter().append(json());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}

	private String json() {
		long data = new Date().getTime();
		return "{\"timestamp\": " + data + ", "
				+ "\"status\": 401, "
				+ "\"error\": \"Apelido ou senha inválidos\", "
				+ "\"path\": \"/login\"}";
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String username = ((UserSpringSecurity) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
	}
	

}
