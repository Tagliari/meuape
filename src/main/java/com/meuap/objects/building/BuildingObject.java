package com.meuap.objects.building;

import java.io.Serializable;
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
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.meuap.objects.GenericAbstractObject;
import com.meuap.objects.info.InfoKeyObject;
import com.meuap.objects.info.InfoValueObject;
import com.meuap.objects.user.UserObject;

@Entity(name = "building")
@Table(name = "building")
public class BuildingObject extends GenericAbstractObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "display_name", nullable = false)
	private String displayName;

	@Column(nullable = false)
	private String street;

	@Column(nullable = false)
	private String neighborhood;

	@Column(nullable = false)
	private String zipcode;

	@Column(nullable = false)
	private String number;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String state;

	@Column(nullable = false)
	private String country;

	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AppartmentObject> appartmentList;

	@ManyToMany(mappedBy = "syndicBuildingList")
	private List<UserObject> syndicUserList;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "building_custom_values", joinColumns = @JoinColumn(name = "building_id"))
	@MapKeyColumn(name = "custom_key")
	@Column(name = "custom_value")
	private Map<String, String> customValues;

	@OneToMany
	@MapKeyClass(InfoKeyObject.class)
	private Map<InfoKeyObject, InfoValueObject> infos;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public List<AppartmentObject> getAppartmentList() {
		return appartmentList;
	}

	public void setAppartmentList(List<AppartmentObject> appartmentList) {
		this.appartmentList = appartmentList;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public List<UserObject> getSyndicUserList() {
		return syndicUserList;
	}

	public void setSyndicUserList(List<UserObject> syndicUserList) {
		this.syndicUserList = syndicUserList;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
