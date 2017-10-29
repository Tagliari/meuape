package com.meuap.objects.gerencianet;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.meuap.objects.GenericAbstractObject;

@Entity(name = "gerencianet_notification")
@Table(name = "gerencianet_notification")
public class NotificationPaymentObject extends GenericAbstractObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "notification_token")
	private String notificationToken;

	@Column(name = "captured")
	private boolean captured;

	@OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<StateTransactionPaymentObject> stateTransactionPaymentObject;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gerencianet_payment_pk")
	private GerencianetBoletoPaymentObject gerencianetPaymentObject;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isCaptured() {
		return captured;
	}

	public void setCaptured(boolean captured) {
		this.captured = captured;
	}

	public List<StateTransactionPaymentObject> getStateTransactionPaymentObject() {
		return stateTransactionPaymentObject;
	}

	public void setStateTransactionPaymentObject(List<StateTransactionPaymentObject> stateTransactionPaymentObject) {
		this.stateTransactionPaymentObject = stateTransactionPaymentObject;
	}

	public String getNotificationToken() {
		return notificationToken;
	}

	public void setNotificationToken(String notificationToken) {
		this.notificationToken = notificationToken;
	}

	public GerencianetBoletoPaymentObject getGerencianetPaymentObject() {
		return gerencianetPaymentObject;
	}

	public void setGerencianetPaymentObject(GerencianetBoletoPaymentObject gerencianetPaymentObject) {
		this.gerencianetPaymentObject = gerencianetPaymentObject;
	}

}