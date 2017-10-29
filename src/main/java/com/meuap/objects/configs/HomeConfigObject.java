package com.meuap.objects.configs;

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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.meuap.objects.user.UserSegmentObject;

@Entity(name = "home_config")
@Table(name = "home_config")
public class HomeConfigObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "enabled")
	private boolean enabled;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "home_config_banner", joinColumns = @JoinColumn(name = "home_config_id"))
	@Column(name = "main_banner_url")
	private List<String> mainBannerUrl;

	/** Unidirectional **/
	@OneToMany
	@JoinColumn(name = "segment_id", referencedColumnName = "id")
	private List<UserSegmentObject> segmentList;

	@Lob
	@Column(name = "header_html")
	private String headerHtml;

	@Lob
	@Column(name = "footer_html")
	private String footerHtml;

	public long getId() { 
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<String> getMain_banner_url() {
		return mainBannerUrl;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<UserSegmentObject> getSegmentList() {
		return segmentList;
	}

	public void setSegmentList(List<UserSegmentObject> segmentList) {
		this.segmentList = segmentList;
	}

	public String getHeaderHtml() {
		return headerHtml;
	}

	public void setHeaderHtml(String headerHtml) {
		this.headerHtml = headerHtml;
	}

	public String getFooterHtml() {
		return footerHtml;
	}

	public void setFooterHtml(String footerHtml) {
		this.footerHtml = footerHtml;
	}

	public List<String> getMainBannerUrl() {
		return mainBannerUrl;
	}

	public void setMainBannerUrl(List<String> mainBannerUrl) {
		this.mainBannerUrl = mainBannerUrl;
	}

}