package com.meuap.objects.gerencianet;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.meuap.objects.GenericAbstractObject;

@Entity(name = "gerencianet_notification_data")
@Table(name = "gerencianet_notification_data")
public class StateTransactionPaymentObject extends GenericAbstractObject implements Serializable {

	/**
	 * 
	 * Example of response:
	 * 
	 **/
	// "code": 200, // retorno HTTP "200" pedido foi bem sucedido
	// "data": [{
	// "id": 1,
	// "type": "charge",
	// "custom_id": null,
	// "status": {
	// "current": "new",
	// "previous": null
	// },
	// "identifiers": {
	// "charge_id": 1002
	// }
	// }]

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "transaction_data_id")
	private String transactionDataId;

	@Column(name = "transaction_data_type")
	private String transactionType;

	@Column(name = "transaction_custom_id")
	private String transactionCustomId;

	@Column(name = "transaction_created_at")
	private String transactionCreatedAt;

	@Column(name = "transaction_current")
	private String transactionCurrent;

	@Column(name = "transaction_previous")
	private String transactionPrevious;

	@Column(name = "transaction_charge_id")
	private String transactionChargeId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@Basic(fetch = FetchType.LAZY)
	@JoinColumn(name = "notification_id")
	private NotificationPaymentObject notification;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTransactionDataId() {
		return transactionDataId;
	}

	public void setTransactionDataId(String transactionDataId) {
		this.transactionDataId = transactionDataId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionCustomId() {
		return transactionCustomId;
	}

	public void setTransactionCustomId(String transactionCustomId) {
		this.transactionCustomId = transactionCustomId;
	}

	public String getTransactionCurrent() {
		return transactionCurrent;
	}

	public void setTransactionCurrent(String transactionCurrent) {
		this.transactionCurrent = transactionCurrent;
	}

	public String getTransactionPrevious() {
		return transactionPrevious;
	}

	public void setTransactionPrevious(String transactionPrevious) {
		this.transactionPrevious = transactionPrevious;
	}

	public String getTransactionChargeId() {
		return transactionChargeId;
	}

	public void setTransactionChargeId(String transactionChargeId) {
		this.transactionChargeId = transactionChargeId;
	}

	public NotificationPaymentObject getNotification() {
		return notification;
	}

	public void setNotification(NotificationPaymentObject notification) {
		this.notification = notification;
	}

	public String getTransactionCreatedAt() {
		return transactionCreatedAt;
	}

	public void setTransactionCreatedAt(String transactionCreatedAt) {
		this.transactionCreatedAt = transactionCreatedAt;
	}

}