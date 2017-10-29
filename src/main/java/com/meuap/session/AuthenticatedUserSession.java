package com.meuap.session;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.meuap.objects.user.UserObject;
import java.io.Serializable;

@SessionScoped
@Named("authenticatedUserSession")
public class AuthenticatedUserSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private UserObject loggedUser;

	public boolean isLogged() {
		return getLoggedUser() != null;
	}

	public UserObject getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(UserObject loggedUser) {
		this.loggedUser = loggedUser;
	}

}
