package com.meuap.objects.configs;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "general_info")
@Table(name = "general_info")
public class GeneralConfigObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "gerencianet_is_hml", nullable = false)
	private boolean gerencianetIsHomologation;

	@Column(name = "gerencianet_hml_url", nullable = false)
	private boolean gerencianetHomologationUrl;

	@Column(name = "gerencianet_prd_url", nullable = false)
	private boolean gerencianetProductionURL;

	@Column(name = "gerencianet_client_id", nullable = false)
	private boolean gerencianetClientId;

	@Column(name = "gerencianet_client_secret", nullable = false)
	private boolean gerencianetClientSecret;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isGerencianetIsHomologation() {
		return gerencianetIsHomologation;
	}

	public void setGerencianetIsHomologation(boolean gerencianetIsHomologation) {
		this.gerencianetIsHomologation = gerencianetIsHomologation;
	}

	public boolean isGerencianetHomologationUrl() {
		return gerencianetHomologationUrl;
	}

	public void setGerencianetHomologationUrl(boolean gerencianetHomologationUrl) {
		this.gerencianetHomologationUrl = gerencianetHomologationUrl;
	}

	public boolean isGerencianetProductionURL() {
		return gerencianetProductionURL;
	}

	public void setGerencianetProductionURL(boolean gerencianetProductionURL) {
		this.gerencianetProductionURL = gerencianetProductionURL;
	}

	public boolean isGerencianetClientId() {
		return gerencianetClientId;
	}

	public void setGerencianetClientId(boolean gerencianetClientId) {
		this.gerencianetClientId = gerencianetClientId;
	}

	public boolean isGerencianetClientSecret() {
		return gerencianetClientSecret;
	}

	public void setGerencianetClientSecret(boolean gerencianetClientSecret) {
		this.gerencianetClientSecret = gerencianetClientSecret;
	}

}
