package com.meuap.objects;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class GenericAbstractObject {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false)
	@CreationTimestamp
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_date", nullable = false)
	@UpdateTimestamp
	private Date lastUpdatedDate;

	@Version
	private int version;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
