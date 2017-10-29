package com.meuap.gerencianet.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class ExampleGerencianet {

	private static String CLIENT_ID = "Client_Id_ce7a43c11a3e2a2130c2f224a9c79dd697b8140a";
	private static String CLIENT_SECRET = "Client_Secret_7af676fbdbaffb21dc71897252b8b2a7a258fab0";

	public static void main(String args[]) {

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", CLIENT_ID);
		options.put("client_secret", CLIENT_SECRET);
		options.put("sandbox", true);

		/* ************************************************* */

		List<Object> items = new ArrayList<Object>();

		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("name", "Item 1");
		item1.put("amount", 1);
		item1.put("value", 1000);

		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("name", "Item 2");
		item2.put("amount", 1);
		item2.put("value", 2000);

		items.add(item1);
		items.add(item2);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("items", items);

		try {
			Gerencianet gn = new Gerencianet(options);
			Map<String, Object> response = gn.call("createCharge", new HashMap<String, String>(), body);
			System.out.println(response);
		} catch (GerencianetException e) {
			System.out.println(e.getCode());
			System.out.println(e.getError());
			System.out.println(e.getErrorDescription());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
