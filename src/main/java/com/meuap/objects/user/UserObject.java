package com.meuap.objects.user;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.meuap.objects.GenericAbstractObject;
import com.meuap.objects.building.AppartmentObject;
import com.meuap.objects.items.ItemObject;
import com.meuap.objects.payment.PaymentObject;

@Entity(name = "user")
@Table(name = "user")
public class UserObject extends GenericAbstractObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "display_name", nullable = false)
	private String displayName;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "personal_type")
	private String personalType;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthdate", nullable = false)
	private Date birthdate;

	@Column(name = "account_type", nullable = false)
	private String accountType;

	@OneToMany(mappedBy = "userObject", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<ItemObject> itemList;

	@ManyToMany
	@JoinTable(name = "renter_by_appartment", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "appartment_id"))
	private List<AppartmentObject> appartmentList;

	@ManyToMany
	@JoinTable(name = "syndic_by_building", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "building_id"))
	private List<AppartmentObject> syndicBuildingList;

	@OneToMany(mappedBy = "userObject", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<PaymentObject> paymentObject;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "user_custom_values", joinColumns = @JoinColumn(name = "user_id"))
	@MapKeyColumn(name = "custom_key")
	@Column(name = "custom_value")
	private Map<String, String> customValues = new HashMap<String, String>();

	@ManyToMany
	@JoinTable(name = "user_by_segment", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "segment_id"))
	private List<UserSegmentObject> segmentList;

	public List<ItemObject> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemObject> itemList) {
		this.itemList = itemList;
	}

	public List<AppartmentObject> getAppartmentList() {
		return appartmentList;
	}

	public void setAppartmentList(List<AppartmentObject> appartmentList) {
		this.appartmentList = appartmentList;
	}

	public List<AppartmentObject> getSyndicBuildingList() {
		return syndicBuildingList;
	}

	public void setSyndicBuildingList(List<AppartmentObject> syndicBuildingList) {
		this.syndicBuildingList = syndicBuildingList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Map<String, String> getCustomValues() {
		return customValues;
	}

	public List<PaymentObject> getPaymentObject() {
		return paymentObject;
	}

	public void setPaymentObject(List<PaymentObject> paymentObject) {
		this.paymentObject = paymentObject;
	}

	public void setCustomValues(Map<String, String> customValues) {
		this.customValues = customValues;
	}

	public List<UserSegmentObject> getSegmentList() {
		return segmentList;
	}

	public void setSegmentList(List<UserSegmentObject> segmentList) {
		this.segmentList = segmentList;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPersonalType() {
		return personalType;
	}

	public void setPersonalType(String personalType) {
		this.personalType = personalType;
	}

}
