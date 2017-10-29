package com.meuap.controller;

import javax.inject.Inject;

import com.meuap.dao.UserDao;
import com.meuap.objects.user.UserObject;
import com.meuap.session.AuthenticatedUserSession;
import com.meuap.utils.LoggerFactory;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Controller
public class UserController {

	private static LoggerFactory logger = LoggerFactory.getInstance(UserController.class);

	private Result result;
	private UserDao userDao;
	private AuthenticatedUserSession authenticatedUserSession;

	public UserController() {
	}

	@Inject
	public UserController(Result result, UserDao userDao, AuthenticatedUserSession authenticatedUserSession) {
		this.result = result;
		this.userDao = userDao;
		this.authenticatedUserSession = authenticatedUserSession;
	}

	@Post("/login")
	public void login(UserObject userObject) {
		logger.info("Will try to find user to login... wait, please");
		UserObject user = userDao.loadUser(userObject);

		if (user == null) {
			logger.info("The user was not found, please verify the data inserted and try again");
			result.use(Results.json()).withoutRoot().from(Boolean.FALSE).serialize();
			return;
		}

		/** Insert logged user into authenticatedUserSession **/
		this.authenticatedUserSession.setLoggedUser(user);

		result.use(Results.json()).withoutRoot().from(Boolean.TRUE).serialize();
	}

	@Path({ "/register", "/register/" })
	public void register() {

	}

	@Post("/register/new")
	public void registerAdminUser(UserObject userObject) {

		logger.info("Will try to register user... wait, please");
		boolean registered = userDao.registerAdminUser(userObject);

		if (registered) {
			logger.info("The user was registered, redirecting to home page....");
			this.result.redirectTo(HomeController.class).home();
			return;
		}
	}

	public AuthenticatedUserSession getAuthenticatedUserSession() {
		return authenticatedUserSession;
	}

	public void setAuthenticatedUserSession(AuthenticatedUserSession authenticatedUserSession) {
		this.authenticatedUserSession = authenticatedUserSession;
	}
}
