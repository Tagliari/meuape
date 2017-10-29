package com.meuap.objects.gerencianet;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.meuap.objects.GenericAbstractObject;
import com.meuap.objects.payment.PaymentBoletoObject;

@Entity(name = "gerencianet_boleto_payment")
@Table(name = "gerencianet_boleto_payment")
public class GerencianetBoletoPaymentObject extends GenericAbstractObject implements Serializable {

	/**
	 * 
	 * Example of response:
	 * 
	 * {code=200, data={total=3000, charge_id=197503, custom_id=null,
	 * created_at=2017-03-12 13:04:14, status=new}}
	 * 
	 **/

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "type")
	private String gnCode;

	@Column(name = "total")
	private String gnTotal;

	@Column(name = "charge_id")
	private String gnChargeId;

	@Column(name = "custom_id")
	private String gnCustomId;

	@Column(name = "created_at")
	private String gnCreatedAt;

	@Column(name = "status")
	private String gnStatus;

	@Column(name = "boleto_code")
	private String gnBoletoCode;

	@Column(name = "boleto_charge_id")
	private String gnBoletoChargeId;

	@Column(name = "boleto_link")
	private String gnBoletoLink;

	@Column(name = "boleto_expire_at")
	private String gnBoletoExpireAt;

	@Column(name = "boleto_payment")
	private String gnBoletoPayment;

	@Column(name = "boleto_barcode")
	private String gnBoletoBarcode;

	@Column(name = "boleto_status")
	private String gnBoletoStatus;

	@OneToOne(mappedBy = "gerencianetBoletoPaymentObject")
	private PaymentBoletoObject paymentBoletoObject;

	@OneToOne(mappedBy = "gerencianetPaymentObject")
	private NotificationPaymentObject notificationPaymentObject;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGnCode() {
		return gnCode;
	}

	public void setGnCode(String gnCode) {
		this.gnCode = gnCode;
	}

	public String getGnTotal() {
		return gnTotal;
	}

	public void setGnTotal(String gnTotal) {
		this.gnTotal = gnTotal;
	}

	public String getGnChargeId() {
		return gnChargeId;
	}

	public void setGnChargeId(String gnChargeId) {
		this.gnChargeId = gnChargeId;
	}

	public String getGnCustomId() {
		return gnCustomId;
	}

	public void setGnCustomId(String gnCustomId) {
		this.gnCustomId = gnCustomId;
	}

	public String getGnCreatedAt() {
		return gnCreatedAt;
	}

	public void setGnCreatedAt(String gnCreatedAt) {
		this.gnCreatedAt = gnCreatedAt;
	}

	public String getGnStatus() {
		return gnStatus;
	}

	public void setGnStatus(String gnStatus) {
		this.gnStatus = gnStatus;
	}

	public PaymentBoletoObject getPaymentBoletoObject() {
		return paymentBoletoObject;
	}

	public void setPaymentBoletoObject(PaymentBoletoObject paymentBoletoObject) {
		this.paymentBoletoObject = paymentBoletoObject;
	}

	public String getGnBoletoCode() {
		return gnBoletoCode;
	}

	public void setGnBoletoCode(String gnBoletoCode) {
		this.gnBoletoCode = gnBoletoCode;
	}

	public String getGnBoletoChargeId() {
		return gnBoletoChargeId;
	}

	public void setGnBoletoChargeId(String gnBoletoChargeId) {
		this.gnBoletoChargeId = gnBoletoChargeId;
	}

	public String getGnBoletoLink() {
		return gnBoletoLink;
	}

	public void setGnBoletoLink(String gnBoletoLink) {
		this.gnBoletoLink = gnBoletoLink;
	}

	public String getGnBoletoExpireAt() {
		return gnBoletoExpireAt;
	}

	public void setGnBoletoExpireAt(String gnBoletoExpireAt) {
		this.gnBoletoExpireAt = gnBoletoExpireAt;
	}

	public String getGnBoletoPayment() {
		return gnBoletoPayment;
	}

	public void setGnBoletoPayment(String gnBoletoPayment) {
		this.gnBoletoPayment = gnBoletoPayment;
	}

	public String getGnBoletoBarcode() {
		return gnBoletoBarcode;
	}

	public void setGnBoletoBarcode(String gnBoletoBarcode) {
		this.gnBoletoBarcode = gnBoletoBarcode;
	}

	public String getGnBoletoStatus() {
		return gnBoletoStatus;
	}

	public void setGnBoletoStatus(String gnBoletoStatus) {
		this.gnBoletoStatus = gnBoletoStatus;
	}

	public NotificationPaymentObject getNotificationPaymentObject() {
		return notificationPaymentObject;
	}

	public void setNotificationPaymentObject(NotificationPaymentObject notificationPaymentObject) {
		this.notificationPaymentObject = notificationPaymentObject;
	}

}