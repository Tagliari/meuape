package com.meuap.gerencianet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.meuap.dao.PaymentDao;
import com.meuap.objects.building.AppartmentObject;
import com.meuap.objects.gerencianet.NotificationPaymentObject;
import com.meuap.objects.items.ItemObject;
import com.meuap.objects.payment.PaymentBoletoObject;
import com.meuap.objects.user.UserObject;
import com.meuap.utils.LoggerFactory;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

/**
 * Controller to generate Boleto
 * 
 */

@ApplicationScoped
@Controller
public class GerencianetController {

	private static LoggerFactory logger = LoggerFactory.getInstance(GerencianetController.class);

	private static String CLIENT_ID = "Client_Id_ce7a43c11a3e2a2130c2f224a9c79dd697b8140a";
	private static String CLIENT_SECRET = "Client_Secret_7af676fbdbaffb21dc71897252b8b2a7a258fab0";
	private static String NOTIFICATION_URL = "http://meuap-tagliari.rhcloud.com/notifications/gerencianet/boleto";
	
	private static boolean SANDBOX = true;

	private static int FINE = 200;
	private static int INTEREST = 33;

	private PaymentDao paymentDao;

	public GerencianetController() {
	}

	@Inject
	public GerencianetController(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	public void sendNewPaymentsBoletoToGerenciaNet() {

		List<PaymentBoletoObject> pgs = paymentDao.paymentsToSendToGateway();
		if (pgs != null) {

			HashMap<String, Object> options = new HashMap<String, Object>();
			options.put("client_id", CLIENT_ID);
			options.put("client_secret", CLIENT_SECRET);
			options.put("sandbox", SANDBOX);

			// for (PaymentBoletoObject pg : pgs) {
			// TODO
			// }
		}

	}

	public void updatePaymentByNotificationHistory(NotificationPaymentObject notification) {

		String token = notification.getNotificationToken();

		HashMap<String, Object> options = createCredentials();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("token", token);

		try {
			Gerencianet gn = new Gerencianet(options);
			Map<String, Object> response = gn.call("getNotification", params, new HashMap<String, Object>());

			logger.info(ToStringBuilder.reflectionToString(response, ToStringStyle.MULTI_LINE_STYLE));

			if (response != null) {
				getPaymentDao().updateNotificationWithResponse(notification, response);
				getPaymentDao().updatePaymentWithNotification(notification, response);
			}
			
			notification.setCaptured(true);
			getPaymentDao().updateNotification(notification);
			
		} catch (Exception e) {
			logger.error("Something happens!", e);
		}
	}

	public HashMap<String, Object> createCredentials() {
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", CLIENT_ID);
		options.put("client_secret", CLIENT_SECRET);
		options.put("sandbox", SANDBOX);

		return options;
	}

	public Map<String, Object> sendPgToGateway(PaymentBoletoObject pg) {
		HashMap<String, Object> options = createCredentials();
		return sendPgToGateway(pg, options);
	}

	public Map<String, Object> sendPgToGateway(PaymentBoletoObject pg, HashMap<String, Object> options) {

		List<Object> mapItems = new ArrayList<Object>();

		List<ItemObject> itemList = pg.getItemList();
		for (ItemObject item : itemList) {

			Map<String, Object> mapItem = new HashMap<String, Object>();
			mapItem.put("name", item.getDisplayName());
			mapItem.put("amount", 1);

			String salePrice = String.valueOf(item.getSalePrice() * 10).replace(".", "");
			mapItem.put("value", Integer.parseInt(salePrice));

			mapItems.add(mapItem);
		}

		Map<String, Object> metadata = new HashMap<String, Object>();
		metadata.put("notification_url", NOTIFICATION_URL + "?paymentId="+ pg.getId());

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("items", mapItems);
		body.put("metadata", metadata);

		try {
			Gerencianet gn = new Gerencianet(options);
			Map<String, Object> response = gn.call("createCharge", new HashMap<String, String>(), body);
			logger.info(ToStringBuilder.reflectionToString(response, ToStringStyle.MULTI_LINE_STYLE));

			response.put("success", true);

			return response;
		} catch (GerencianetException e) {
			logger.error("Something happens, GerencianetException was returned!", e);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("code", e.getCode());
			map.put("error", e.getError());
			map.put("errorDescription", e.getErrorDescription());

			return map;

		} catch (Exception e) {
			logger.error("Something happens!", e);
		}

		return null;
	}

	public Map<String, Object> changeGerencianetTransationToBoleto(PaymentBoletoObject pg) {
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", CLIENT_ID);
		options.put("client_secret", CLIENT_SECRET);
		options.put("sandbox", true);

		return changeGerencianetTransationToBoleto(pg, options);
	}

	public Map<String, Object> changeGerencianetTransationToBoleto(PaymentBoletoObject pg, HashMap<String, Object> options) {
		try {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("id", pg.getGerencianetBoletoPaymentObject().getGnChargeId());

			Map<String, Object> address = new HashMap<String, Object>();

			AppartmentObject ap = (AppartmentObject) paymentDao.getHibernateControl().getSession().load(AppartmentObject.class, pg.getAppartment().getId());

			address.put("street", ap.getBuilding().getStreet());
			address.put("number", ap.getBuilding().getNumber());
			address.put("neighborhood", ap.getBuilding().getNeighborhood());
			address.put("zipcode", ap.getBuilding().getZipcode());
			address.put("city", ap.getBuilding().getCity());
			address.put("complement", "Apartamento " + ap.getNumber());
			address.put("state", ap.getBuilding().getState().substring(0, 2));

			Map<String, Object> customer = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

			UserObject user = (UserObject) paymentDao.getHibernateControl().getSession().load(UserObject.class, pg.getUserObject().getId());

			customer.put("name", user.getDisplayName());
			customer.put("cpf", user.getCpf());
			customer.put("email", user.getEmail());
			customer.put("phone_number", user.getPhone());
			customer.put("address", address);
			customer.put("birth", sdf.format(user.getBirthdate()));

			Map<String, Object> configurations = new HashMap<String, Object>();
			configurations.put("fine", FINE);
			configurations.put("interest", INTEREST);

			Map<String, Object> bankingBillet = new HashMap<String, Object>();
			bankingBillet.put("expire_at", sdf.format(pg.getExpirationDate()));
			bankingBillet.put("message", pg.getObservations());
			bankingBillet.put("customer", customer);
			bankingBillet.put("configurations", configurations);

			Map<String, Object> payment = new HashMap<String, Object>();
			payment.put("banking_billet", bankingBillet);

			Map<String, Object> body = new HashMap<String, Object>();
			body.put("payment", payment);

			Gerencianet gn = new Gerencianet(options);
			Map<String, Object> response = gn.call("payCharge", params, body);
			/**
			 * 
			 * {code=200, data={total=126700, charge_id=199781,
			 * link=https://visualizacaosandbox.gerencianet.com.br/emissao/107980_1_RRATA2/A4XB-107980-143721-LECA2,
			 * expire_at=2017-03-23, payment=banking_billet, barcode=00000.00000
			 * 00000.000000 00000.000000 0 00000000000000, status=waiting}}
			 * 
			 **/
			response.put("success", true);

			return response;
		} catch (GerencianetException e) {
			logger.error("Something happens, GerencianetException was returned!", e);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("code", e.getCode());
			map.put("error", e.getError());
			map.put("errorDescription", e.getErrorDescription());

			return map;
		} catch (Exception e) {
			logger.error("Something happens!", e);
		}

		return null;
	}

	@Path({ "/notifications/gerencianet/boleto/", "/notifications/gerencianet/boleto" })
	@Post
	public void gerencianetNotification(HttpServletRequest request, HttpServletResponse response ) {
		
		String notification = request.getParameter("notification");
		if (StringUtils.isBlank(notification)) {
			request.getAttribute("notification");
		}
		
		String paymentId = request.getParameter("paymentId");
		
		try {
			logger.info("Persisting a notification token eq=" + notification);
			if (StringUtils.isNotBlank(notification)) {
				getPaymentDao().persistNewNotification(notification, paymentId);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public PaymentDao getPaymentDao() {
		return paymentDao;
	}

	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}
}
