package br.pokemon.seguranca;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.pokemon.enums.Perfil;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserSpringSecurity implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String apelido;
	
	private String senha;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSpringSecurity(Long id, String apelido, String senha,
			Set<Perfil> perfis) {
		super();
		this.id = id;
		this.apelido = apelido;
		this.senha = senha;
		this.authorities = perfis.stream().map(p -> new SimpleGrantedAuthority(p.getDescricao())).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return apelido;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
