package com.meuap.objects.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.meuap.objects.GenericAbstractObject;

@Entity(name = "user_segment")
@Table(name = "user_segment")
public class UserSegmentObject extends GenericAbstractObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "segment_name", nullable = false)
	private String segmentName;

	@ManyToMany(mappedBy = "segmentList")
	@Column(name = "user_id")
	private List<UserObject> userList;

	/** Unidirectional **/
	@Column(name = "home_config_id")
	private String homeConfigId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public List<UserObject> getUserList() {
		return userList;
	}

	public void setUserList(List<UserObject> userList) {
		this.userList = userList;
	}

	public String getHomeConfigId() {
		return homeConfigId;
	}

	public void setHomeConfigId(String homeConfigId) {
		this.homeConfigId = homeConfigId;
	}

}