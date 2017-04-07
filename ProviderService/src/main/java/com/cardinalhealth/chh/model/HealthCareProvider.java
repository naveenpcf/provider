package com.cardinalhealth.chh.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the "HCP" database table.
 * This class is capable of accepting UUIDs if passed in request. 
 * If no UUID is detected it will generate one.
 */

@Entity
@Table(name = "HCP", schema = "CARES")
public class HealthCareProvider implements Serializable {

	private static final long serialVersionUID = 2188708667044421366L;

	@Id
	@GeneratedValue(generator = "provider-uuid")
	@GenericGenerator(name = "provider-uuid", strategy = "com.cardinalhealth.chh.config.ProviderUUIDGenerator")
	@Column(name = "HCP_ID")
	private UUID hcpId;
	
	/**
	 * @return the hcpId
	 */
	public UUID getHcpId() {
		return hcpId;
	}

	/**
	 * @param hcpId the hcpId to set
	 */
	public void setHcpId(UUID hcpId) {
		this.hcpId = hcpId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HealthCareProvider [hcpId=" + hcpId + "]";
	}
}