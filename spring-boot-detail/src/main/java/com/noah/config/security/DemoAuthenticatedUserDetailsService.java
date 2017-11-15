package com.noah.config.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class DemoAuthenticatedUserDetailsService
		implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoAuthenticatedUserDetailsService.class);

	// we maintain the entitlements in a map
	public static Map<String, List<String>> entitlementMap = new HashMap<>();

	static {
		entitlementMap.put("noah_123456", Arrays.asList(Role.ADMIN, Role.READ_ONLY));
		entitlementMap.put("allie_654321", Arrays.asList(Role.READ_WRITE));
		entitlementMap.put("Xman_999999", new ArrayList<>());
	}

	@Override
	public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
		// the token from the PreAuthenticatedProcessingFilter is being used here
		String principal = (String) token.getPrincipal();
		String[] parts = principal.split("_", 2);
		String userName = parts[0];
		String pwd = parts[1];
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		List<String> roles = entitlementMap.get(principal);
		if (roles != null) {
			roles.forEach(role -> {
				grantedAuthorities.add(new SimpleGrantedAuthority(Role.PREFIX + role));
			});
		}
		LOGGER.info("User {} with roles {} ", userName, roles);
		return new User(userName, pwd, grantedAuthorities);
	}

}
