package com.meuap.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.meuap.dao.PaymentDao;
import com.meuap.dao.UserDao;
import com.meuap.exception.DaoException;
import com.meuap.gerencianet.GerencianetController;
import com.meuap.objects.gerencianet.GerencianetBoletoPaymentObject;
import com.meuap.objects.items.ItemObject;
import com.meuap.objects.payment.PaymentBoletoObject;
import com.meuap.objects.payment.PaymentObject;
import com.meuap.objects.user.UserObject;
import com.meuap.utils.LoggerFactory;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Controller
public class PaymentController {

	private static LoggerFactory logger = LoggerFactory.getInstance(PaymentController.class);

	private Result result;
	private PaymentDao paymentDao;
	private UserDao userDao;
	private UserController userController;
	private ResourceBundle bundle;
	private GerencianetController gerencianetController;

	public PaymentController() {
		this.bundle = ResourceBundle.getBundle("com.meuap.Resources");
	}

	@Inject
	public PaymentController(Result result, PaymentDao paymentDao, UserDao userDao, UserController userController, GerencianetController gerencianetController) {
		this.bundle = ResourceBundle.getBundle("com.meuap.Resources");
		this.result = result;
		this.paymentDao = paymentDao;
		this.userDao = userDao;
		this.userController = userController;
		this.gerencianetController = gerencianetController;
	}

	@Path({ "/admin/payment", "/admin/payment/" })
	public void payment() {
		logger.debug("Will verify payments or create new one.");

		List<UserObject> listAllUsers = new CopyOnWriteArrayList<UserObject>(userDao.listAllUsers());

		Iterator<UserObject> users = listAllUsers.iterator();

		while (users.hasNext()) {
			UserObject user = users.next();
			if (user.getAppartmentList().isEmpty()) {
				listAllUsers.remove(user);
				result.include("message", bundle.getString("java_payment_user_does_not_have_appartments"));
			}
		}

		result.include("allPaymentBoleto", paymentDao.listAllPaymentBoleto());
		result.include("userList", listAllUsers);
	}

	@Path({ "/admin/payment/boleto/all", "/admin/payment/all/" })
	@Post
	public void listAllBoletoPayments() {
		result.include("allPaymentBoleto", paymentDao.listAllPaymentBoleto());
	}

	@SuppressWarnings("unchecked")
	@Path({ "/admin/payment/boleto/create", "/admin/payment/create/" })
	@Post
	public void createNewBoletoPayment(PaymentBoletoObject pg) {

		try {
			pg.setType("boleto");

			List<ItemObject> itemList = pg.getItemList();
			if (itemList != null && itemList.size() > 0) {
				for (ItemObject item : itemList) {

					if (item.getSalePrice() == 0D) {
						item.setSalePrice(item.getListPrice());
						continue;
					}

					if (item.getListPrice() == 0D) {
						item.setListPrice(item.getSalePrice());
						continue;
					}
				}
			}

			/**
			 * If amount of pg was not settled, will calculate automatically
			 **/
			if (pg.getAmount() <= 0) {
				double amount = calculateAmountPayment(pg);
				pg.setAmount(amount);
				pg.setInstallmentAmount(amount);
				pg.setInstallmentQtd(1);
			}

			Map<String, Object> transactionBoleto = null;
			Map<String, Object> gerencianetResponse = getGerencianetController().sendPgToGateway(pg);

			if (gerencianetResponse == null) {
				logger.error("Returning error to create gerencianet transaction!");

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("success", false);
				map.put("message", bundle.getString("java_payment_create_payment_boleto_error"));
				result.use(Results.json()).withoutRoot().from(map).serialize();

				return;
			}

			boolean success = (boolean) gerencianetResponse.get("success");
			if (success) {
				GerencianetBoletoPaymentObject gnBoletoPg = new GerencianetBoletoPaymentObject();

				if (gerencianetResponse.containsKey("code")) {
					gnBoletoPg.setGnCode(String.valueOf(gerencianetResponse.get("code")));
				}

				Map<String, String> gerencianetData = (Map<String, String>) gerencianetResponse.get("data");

				if (gerencianetData.containsKey("charge_id")) {
					gnBoletoPg.setGnChargeId(String.valueOf(gerencianetData.get("charge_id")));
				}
				if (gerencianetData.containsKey("created_at")) {
					gnBoletoPg.setGnCreatedAt(gerencianetData.get("created_at"));
				}
				if (gerencianetData.containsKey("custom_id")) {
					gnBoletoPg.setGnCustomId(gerencianetData.get("custom_id"));
				}
				if (gerencianetData.containsKey("status")) {
					gnBoletoPg.setGnStatus(gerencianetData.get("status"));
				}

				pg.setGerencianetBoletoPaymentObject(gnBoletoPg);

				transactionBoleto = getGerencianetController().changeGerencianetTransationToBoleto(pg);
			}

			if (transactionBoleto == null) {

				logger.error("Returning error when change gerencianet transaction to boleto!");

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("success", false);
				map.put("message", bundle.getString("java_payment_create_payment_boleto_error"));
				result.use(Results.json()).withoutRoot().from(map).serialize();

				return;
			}

			boolean boletoSuccess = (boolean) transactionBoleto.get("success");
			if (!boletoSuccess) {
				transactionBoleto.put("message", transactionBoleto.get("errorDescription"));

				result.use(Results.json()).withoutRoot().from(transactionBoleto).serialize();
				return;
			}

			if (transactionBoleto.containsKey("code")) {
				pg.getGerencianetBoletoPaymentObject().setGnBoletoCode(String.valueOf(transactionBoleto.get("code")));
			}

			Map<String, String> boletoData = (Map<String, String>) transactionBoleto.get("data");
			if (boletoData.containsKey("charge_id")) {
				pg.getGerencianetBoletoPaymentObject().setGnBoletoChargeId(String.valueOf(boletoData.get("charge_id")));
			}
			if (boletoData.containsKey("link")) {
				pg.getGerencianetBoletoPaymentObject().setGnBoletoLink((String) boletoData.get("link"));
			}
			if (boletoData.containsKey("expire_at")) {
				pg.getGerencianetBoletoPaymentObject().setGnBoletoExpireAt((String) boletoData.get("expire_at"));
			}
			if (boletoData.containsKey("payment")) {
				pg.getGerencianetBoletoPaymentObject().setGnBoletoPayment((String) boletoData.get("payment"));
			}
			if (boletoData.containsKey("barcode")) {
				pg.getGerencianetBoletoPaymentObject().setGnBoletoBarcode((String) boletoData.get("barcode"));
				pg.setBarcode((String) boletoData.get("barcode"));
			}
			if (boletoData.containsKey("status")) {
				pg.getGerencianetBoletoPaymentObject().setGnBoletoStatus((String) boletoData.get("status"));
			}

			logger.debug("Creating a new payment with these values: ");
			logger.debug(ToStringBuilder.reflectionToString(pg, ToStringStyle.MULTI_LINE_STYLE));

			pg.setState("TO_CAPTURE_WAITING");
			pg.setStateDescription("PAYMENT WAS CREATED, WAITING TO BE PAYED");
			
			this.paymentDao.createPayment(pg);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			map.put("message", bundle.getString("java_payment_create_payment_success"));
			result.use(Results.json()).withoutRoot().from(map).serialize();

		} catch (DaoException e) {
			logger.error("Returning error when access database");

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("message", bundle.getString("java_payment_create_payment_dao_error"));
			result.use(Results.json()).withoutRoot().from(map).serialize();

		} catch (Exception e) {
			logger.error("Something wrong!", e);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("message", bundle.getString("java_payment_create_payment_exception"));
			result.use(Results.json()).withoutRoot().from(map).serialize();
		}
	}

	private double calculateAmountPayment(PaymentObject pg) {
		double amount = 0D;
		List<ItemObject> itemList = pg.getItemList();
		for (ItemObject item : itemList) {
			amount += item.getSalePrice();
		}

		return amount;
	}

	@Path({ "/admin/payment/boleto/select/user", "/admin/payment/select/user/" })
	@Post
	public void selectUserBoletoPayment(PaymentBoletoObject pg) {
		if (pg == null || pg.getUserObject() == null || pg.getUserObject().getId() == 0) {
			return;
		}

		logger.debug("The User selected is eq=" + pg.getUserObject().getId());

		UserObject userObject = new UserObject();
		userObject.setId(pg.getUserObject().getId());

		UserObject user = userDao.findUser(userObject);

		result.include("itemList", user.getItemList());
		result.include("appartmentList", user.getAppartmentList());

	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public PaymentDao getPaymentDao() {
		return paymentDao;
	}

	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public GerencianetController getGerencianetController() {
		return gerencianetController;
	}

	public void setGerencianetController(GerencianetController gerencianetController) {
		this.gerencianetController = gerencianetController;
	}

}
