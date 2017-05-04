package sprssozdemo1.sprssoLogin.sprsec;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;


public class MyUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 5498774440768545721L;

	public MyUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public MyUsernamePasswordAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}
