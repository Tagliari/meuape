package com.meuap.gerencianet.scheduler;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.meuap.gerencianet.GerencianetController;
import com.meuap.objects.gerencianet.NotificationPaymentObject;

import br.com.caelum.vraptor.tasks.Task;
import br.com.caelum.vraptor.tasks.scheduler.Scheduled;

@ApplicationScoped
@Scheduled(id = "CapturePgsFromGerencianetScheduler", cron = "0 * * 1/1 * ? *", concurrent = false)
public class UpdatePgsByGerencianetScheduler implements Task {

	@Inject private GerencianetController gerencianetController;

	public UpdatePgsByGerencianetScheduler() {
	}

	public void execute() {
		
		List<NotificationPaymentObject> notifications = getGerencianetController().getPaymentDao().getAllNotificationsNotCapturedYet();
		if (notifications != null) {
			for (NotificationPaymentObject notification : notifications) {
				getGerencianetController().updatePaymentByNotificationHistory(notification);
			}
		}

	}

	public GerencianetController getGerencianetController() {
		return gerencianetController;
	}

	public void setGerencianetController(GerencianetController gerencianetController) {
		this.gerencianetController = gerencianetController;
	}
}