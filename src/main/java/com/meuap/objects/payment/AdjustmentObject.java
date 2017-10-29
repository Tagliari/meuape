package com.meuap.objects.payment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.meuap.objects.GenericAbstractObject;

@Entity(name = "adjustment")
@Table(name = "adjustment")
public class AdjustmentObject extends GenericAbstractObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(nullable = false)
	private String displayName;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private double amount;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "item_custom_values", joinColumns = @JoinColumn(name = "item_id"))
	@MapKeyColumn(name = "custom_key")
	@Column(name = "custom_value")
	private Map<String, String> customValues = new HashMap<String, String>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getCustomValues() {
		return customValues;
	}

	public void setCustomValues(Map<String, String> customValues) {
		this.customValues = customValues;
	}

}