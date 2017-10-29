package com.meuap.objects.info;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.meuap.objects.GenericAbstractObject;

@Entity(name = "info_value")
@Table(name = "info_value")
public class InfoValueObject extends GenericAbstractObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private long id;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "info_value_content")
	@Column(name = "info_content")
	private List<String> infoContent;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<String> getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(List<String> infoContent) {
		this.infoContent = infoContent;
	}

}
