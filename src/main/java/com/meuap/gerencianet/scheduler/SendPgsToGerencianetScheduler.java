package com.meuap.gerencianet.scheduler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.meuap.gerencianet.GerencianetController;

import br.com.caelum.vraptor.tasks.Task;
import br.com.caelum.vraptor.tasks.scheduler.Scheduled;

@ApplicationScoped
@Scheduled(id = "SendToGerencianetScheduler", cron = "0 0/30 * 1/1 * ? *", concurrent = false)
public class SendPgsToGerencianetScheduler implements Task {

	private GerencianetController gerencianetController;

	public SendPgsToGerencianetScheduler() {
	}
	
	@Inject
	public SendPgsToGerencianetScheduler(GerencianetController gerencianetController) {
		this.gerencianetController = gerencianetController;
	}

	public void execute() {

	}

	public GerencianetController getGerencianetController() {
		return gerencianetController;
	}

	public void setGerencianetController(GerencianetController gerencianetController) {
		this.gerencianetController = gerencianetController;
	}
}