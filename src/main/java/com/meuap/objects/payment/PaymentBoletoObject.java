package com.meuap.objects.payment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.meuap.objects.gerencianet.GerencianetBoletoPaymentObject;

@Entity(name = "payment_boleto")
@Table(name = "payment_boleto")
public class PaymentBoletoObject extends PaymentObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "barcode")
	private String barcode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiration_date", nullable = false)
	private Date expirationDate;

	@Column(name = "observations")
	private String observations;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gerencianet_boleto_payment_pk")
	private GerencianetBoletoPaymentObject gerencianetBoletoPaymentObject;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public GerencianetBoletoPaymentObject getGerencianetBoletoPaymentObject() {
		return gerencianetBoletoPaymentObject;
	}

	public void setGerencianetBoletoPaymentObject(GerencianetBoletoPaymentObject gerencianetBoletoPaymentObject) {
		this.gerencianetBoletoPaymentObject = gerencianetBoletoPaymentObject;
	}
}
