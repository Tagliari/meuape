package com.meuap.objects.payment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import com.meuap.objects.GenericAbstractObject;
import com.meuap.objects.building.AppartmentObject;
import com.meuap.objects.items.ItemObject;
import com.meuap.objects.user.UserObject;

@Entity(name = "payment")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PaymentObject extends GenericAbstractObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "state_description", nullable = true)
	private String stateDescription;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "installment_qtd", nullable = false)
	private int installmentQtd;

	@Column(name = "installment_amount", nullable = false)
	private double installmentAmount;

	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemObject> itemList = new ArrayList<ItemObject>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<AdjustmentObject> adjustmentList = new ArrayList<AdjustmentObject>();

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserObject userObject = new UserObject();

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "appartment_id")
	private AppartmentObject appartment = new AppartmentObject();

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "payment_custom_values", joinColumns = @JoinColumn(name = "custom_value_id"))
	@MapKeyColumn(name = "custom_key")
	@Column(name = "custom_value")
	private Map<String, String> customValues = new HashMap<String, String>();

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateDescription() {
		return stateDescription;
	}

	public void setStateDescription(String stateDescription) {
		this.stateDescription = stateDescription;
	}

	public Map<String, String> getCustomValues() {
		return customValues;
	}

	public void setCustomValues(Map<String, String> customValues) {
		this.customValues = customValues;
	}

	public UserObject getUserObject() {
		return userObject;
	}

	public void setUserObject(UserObject userObject) {
		this.userObject = userObject;
	}

	public AppartmentObject getAppartment() {
		return appartment;
	}

	public void setAppartment(AppartmentObject appartment) {
		this.appartment = appartment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ItemObject> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemObject> itemList) {
		this.itemList = itemList;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<AdjustmentObject> getAdjustmentList() {
		return adjustmentList;
	}

	public void setAdjustmentList(List<AdjustmentObject> adjustmentList) {
		this.adjustmentList = adjustmentList;
	}

	public double getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(double installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public int getInstallmentQtd() {
		return installmentQtd;
	}

	public void setInstallmentQtd(int installmentQtd) {
		this.installmentQtd = installmentQtd;
	}

}