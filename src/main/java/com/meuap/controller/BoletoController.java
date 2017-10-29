package com.meuap.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.inject.Inject;

import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;

import com.meuap.payment.BoletoTools;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.InputStreamDownload;

/**
 * Controller to generate Boleto
 * 
 */

@Controller
public class BoletoController {

	@Inject
	private BoletoTools boletoFactory;

	@Path(value = "/boleto/download")
	public Download download() {

		Boleto boleto = getBoletoFactory().createDefaultBoleto();
		byte[] pdfAsBytes = new BoletoViewer(boleto).getPdfAsByteArray();

		InputStream inputStream = new ByteArrayInputStream(pdfAsBytes);
		Download download = new InputStreamDownload(inputStream, "application/pdf", "boleto.pdf", true, pdfAsBytes.length);

		return download;
	}

	public BoletoTools getBoletoFactory() {
		return boletoFactory;
	}

	public void setBoletoFactory(BoletoTools boletoFactory) {
		this.boletoFactory = boletoFactory;
	}
}
