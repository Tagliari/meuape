package com.meuap.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.meuap.HibernateControl;
import com.meuap.exception.DaoException;
import com.meuap.objects.gerencianet.GerencianetBoletoPaymentObject;
import com.meuap.objects.gerencianet.NotificationPaymentObject;
import com.meuap.objects.gerencianet.StateTransactionPaymentObject;
import com.meuap.objects.items.ItemObject;
import com.meuap.objects.payment.PaymentBoletoObject;
import com.meuap.objects.payment.PaymentObject;
import com.meuap.utils.LoggerFactory;

@ApplicationScoped
@TransactionManagement(value = TransactionManagementType.BEAN)
public class PaymentDao {

	private static LoggerFactory logger = LoggerFactory.getInstance(PaymentDao.class);

	@Inject
	private HibernateControl hibernateControl;

	public PaymentDao() {
	}

	public void createPayment(PaymentObject pg) throws DaoException {
		try {
			hibernateControl.persist(pg);
		} catch (Exception e) {
			logger.error("Fail to persist payment :(", e);

			throw new DaoException(e);
		}
	}

	public void createItem(ItemObject item) throws DaoException {
		try {
			hibernateControl.persist(item);
		} catch (Exception e) {
			logger.error("Fail to persist itemObject of payment :(", e);

			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<PaymentBoletoObject> listAllPaymentBoleto() {
		try {
			Session session = hibernateControl.getSession();
			Criteria criteria = session.createCriteria(PaymentBoletoObject.class);

			criteria = criteria.addOrder(Order.desc("lastUpdatedDate"));
			List<PaymentBoletoObject> pgBoletoList = criteria.list();

			if (pgBoletoList != null) {
				return pgBoletoList;
			}

		} catch (Exception e) {
			logger.error("Fail to list all PaymentBoleto :(", e);
		}
		return new ArrayList<PaymentBoletoObject>();
	}

	@SuppressWarnings("unchecked")
	public List<PaymentBoletoObject> paymentsToSendToGateway() {
		try {
			Session session = hibernateControl.getSession();
			Criteria criteria = session.createCriteria(PaymentBoletoObject.class);

			SimpleExpression submitted = Restrictions.eq("email", "SUBMITTED");
			SimpleExpression pgType = Restrictions.eq("type", "boleto");

			criteria = criteria.add(submitted).add(pgType).addOrder(Order.desc("lastUpdatedDate"));
			List<PaymentBoletoObject> pgBoletoList = criteria.list();

			if (pgBoletoList != null && pgBoletoList.size() > 0) {
				return pgBoletoList;
			}

		} catch (Exception e) {
			logger.error("Fail to send all payments to gateway :(", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<PaymentBoletoObject> paymentsToGetResponseFromGateway() {
		try {
			Session session = hibernateControl.getSession();
			Criteria criteria = session.createCriteria(PaymentBoletoObject.class);

			SimpleExpression submitted = Restrictions.eq("email", "TO_CAPTURE");
			SimpleExpression pgType = Restrictions.eq("type", "boleto");

			criteria = criteria.add(submitted).add(pgType).addOrder(Order.desc("lastUpdatedDate"));
			List<PaymentBoletoObject> pgBoletoList = criteria.list();

			if (pgBoletoList != null && pgBoletoList.size() > 0) {
				return pgBoletoList;
			}

		} catch (Exception e) {
			logger.error("Fail to get response from gateway :(", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<NotificationPaymentObject> getAllNotificationsNotCapturedYet() {
		try {
			Session session = hibernateControl.getSession();
			Criteria criteria = session.createCriteria(NotificationPaymentObject.class);

			SimpleExpression captured = Restrictions.eq("captured", false);

			criteria = criteria.add(captured);
			List<NotificationPaymentObject> notificationPayments = criteria.list();

			if (notificationPayments != null && notificationPayments.size() > 0) {
				return notificationPayments;
			}

		} catch (Exception e) {
			logger.error("Fail to get element from database :(", e);
		}
		return null;
	}

	public void persistNewNotification(String notification, String paymentId) throws DaoException {

		try {
			NotificationPaymentObject notificationObject = new NotificationPaymentObject();
			notificationObject.setNotificationToken(notification);

			if (StringUtils.isNotBlank(paymentId)) {
				GerencianetBoletoPaymentObject paymentObj = new GerencianetBoletoPaymentObject();
				paymentObj.setId(Integer.parseInt(paymentId));
				notificationObject.setGerencianetPaymentObject(paymentObj);
			}

			hibernateControl.persist(notificationObject);
		} catch (Exception e) {
			logger.error("Fail to persist notification received! :(", e);

			throw new DaoException(e);
		}
	}

	public void updateNotification(NotificationPaymentObject notification) throws DaoException {
		try {
			hibernateControl.update(notification);

		} catch (Exception e) {
			logger.error("Fail to update Notification :(", e);

			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void updateNotificationWithResponse(NotificationPaymentObject notificationPayment, Map<String, Object> response) throws Exception {
		try {
			StateTransactionPaymentObject stPaymentObj = new StateTransactionPaymentObject();

			boolean containsKey = response.containsKey("data");
			if (!containsKey) {
				logger.info("The 'data' was not found in response gerencianet! please verify");
				return;
			}
			ArrayList<Map<String, Object>> historyMap = (ArrayList<Map<String, Object>>) response.get("data");

			Integer last = historyMap.size() - 1;

			Map<String, Object> identifiersMap = (Map<String, Object>) historyMap.get(last).get("identifiers");
			Integer chargeId = (Integer) identifiersMap.get("charge_id");

			Integer customId = (Integer) historyMap.get(last).get("custom_id");
			String createdAt = (String) historyMap.get(last).get("created_at");
			String type = (String) historyMap.get(last).get("type");
			Integer id = (Integer) historyMap.get(last).get("id");

			Map<String, Object> statusMap = (Map<String, Object>) historyMap.get(last).get("status");
			String currentStatus = (String) statusMap.get("current");
			String previousStatus = (String) statusMap.get("previous");

			if (chargeId != null) {
				stPaymentObj.setTransactionChargeId(chargeId.toString());
			}
			if (customId != null) {
				stPaymentObj.setTransactionCustomId(customId.toString());
			}

			stPaymentObj.setTransactionDataId(id.toString());
			stPaymentObj.setTransactionCurrent(currentStatus);
			stPaymentObj.setTransactionPrevious(previousStatus);
			stPaymentObj.setTransactionCreatedAt(createdAt);
			stPaymentObj.setTransactionType(type);

			List<StateTransactionPaymentObject> stpg = notificationPayment.getStateTransactionPaymentObject();
			if (stpg == null || stpg.isEmpty()) {
				stpg = new ArrayList<StateTransactionPaymentObject>();
				stpg.add(stPaymentObj);

				notificationPayment.setStateTransactionPaymentObject(stpg);
			}

			hibernateControl.update(notificationPayment);
		} catch (Exception e) {
			logger.error("" + e);
			logger.error("Something happens to update notification Payment");
			throw e;
		}
	}

	public void updatePaymentWithNotification(NotificationPaymentObject notification, Map<String, Object> response) throws Exception {
		try {

			List<StateTransactionPaymentObject> stateTransactions = notification.getStateTransactionPaymentObject();
			if (stateTransactions == null || stateTransactions.isEmpty()) {
				return;
			}

			StateTransactionPaymentObject lastPayment = stateTransactions.get(0);
			for (StateTransactionPaymentObject stateTransaction : stateTransactions) {
				Integer id = Integer.parseInt(stateTransaction.getTransactionDataId());
				Integer lastId = Integer.parseInt(lastPayment.getTransactionDataId());
				if (id > lastId) {
					lastPayment = stateTransaction;
				}
			}

			Session session = hibernateControl.getSession();
			Criteria criteria = session.createCriteria(GerencianetBoletoPaymentObject.class);

			String transactionChargeId = stateTransactions.get(0).getTransactionChargeId();
			SimpleExpression chargeId = Restrictions.eq("gnChargeId", transactionChargeId);

			criteria = criteria.add(chargeId);
			GerencianetBoletoPaymentObject boletoPayment = (GerencianetBoletoPaymentObject) criteria.uniqueResult();

			if (boletoPayment == null) {
				return;
			}

			boletoPayment.setNotificationPaymentObject(notification);

			String status = lastPayment.getTransactionCurrent();
			boletoPayment.setGnBoletoStatus(status);

			hibernateControl.update(boletoPayment);

		} catch (Exception e) {
			logger.error("Fail to get element from database :(", e);
			throw e;
		}

	}

	public HibernateControl getHibernateControl() {
		return hibernateControl;
	}

	public void setHibernateControl(HibernateControl hibernateControl) {
		this.hibernateControl = hibernateControl;
	}

}
