package com.meuap.objects.building;

import java.io.Serializable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.meuap.objects.GenericAbstractObject;
import com.meuap.objects.payment.PaymentObject;
import com.meuap.objects.user.UserObject;

@Entity(name = "appartment")
@Table(name = "appartment")
public class AppartmentObject extends GenericAbstractObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(nullable = false)
	private String number;

	@Column(nullable = false)
	private String floor;

	@ManyToMany(mappedBy = "appartmentList", fetch = FetchType.LAZY)
	private List<UserObject> userList;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@Basic(fetch=FetchType.LAZY)
	@JoinColumn(name = "building_id")
	private BuildingObject building;

	@OneToMany(mappedBy = "appartment", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<PaymentObject> paymentList;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "appartment_custom_values", joinColumns = @JoinColumn(name = "appartment_id"))
	@MapKeyColumn(name = "custom_key")
	@Column(name = "custom_value")
	private Map<String, String> customValues = new HashMap<String, String>();

	public List<UserObject> getUserList() {
		return userList;
	}

	public void setUserList(List<UserObject> userList) {
		this.userList = userList;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public BuildingObject getBuilding() {
		return building;
	}

	public void setBuilding(BuildingObject building) {
		this.building = building;
	}

	public List<PaymentObject> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<PaymentObject> paymentList) {
		this.paymentList = paymentList;
	}

	public Map<String, String> getCustomValues() {
		return customValues;
	}

	public void setCustomValues(Map<String, String> customValues) {
		this.customValues = customValues;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
