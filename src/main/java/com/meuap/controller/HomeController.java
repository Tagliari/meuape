package com.meuap.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.meuap.dao.ConfigDao;
import com.meuap.gerencianet.scheduler.SendPgsToGerencianetScheduler;
import com.meuap.objects.configs.HomeConfigObject;
import com.meuap.utils.LoggerFactory;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

@ApplicationScoped
@Controller
public class HomeController {

	private static LoggerFactory logger = LoggerFactory.getInstance(UserController.class);

	private Result result;
	private ConfigDao configDao;

	public HomeController() {
		this(null, null);
	}

	@Inject
	public HomeController(Result result, ConfigDao configDao) {

		SendPgsToGerencianetScheduler sg = new SendPgsToGerencianetScheduler();
		sg.execute();

		this.result = result;
		this.configDao = configDao;
	}

	public void getHomeConfig() {
		logger.debug("Getting Home Configuration..");
		HomeConfigObject homeConfig = configDao.loadHomeConfig();
		result.include("homeConfig", homeConfig);
	}

	@Path("/")
	public void home() {
		getHomeConfig();
	}

}
