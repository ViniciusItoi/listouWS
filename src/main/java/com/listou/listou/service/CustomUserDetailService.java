package com.listou.listou.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.listou.listou.entity.UserListou;
import com.listou.listou.repository.UserListouRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {
	
	private final UserListouRepository userListouRepository;
	
	@Autowired
	public CustomUserDetailService(UserListouRepository userListouRepository) {
		this.userListouRepository = userListouRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserListou userListou =  Optional.ofNullable(userListouRepository.findByUsername(username))
				.orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado"));
		List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
		List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
	return new User(userListou.getUsername(), userListou.getPassword(), userListou.isAdmin() ? authorityListAdmin:authorityListUser);
	}

}
