package com.cardinalhealth.chh.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the "HCP_HIST" database table.
 * This class is capable of accepting UUIDs if passed in request. 
 * If no UUID is detected it will generate one.
 * 
 * @author guru
 */
@Entity
@Table(name = "HCP_HIST", schema = "CARES")
public class HealthCareProviderHistory implements Serializable {

	private static final long serialVersionUID = 2188708667044421366L;

	@Id
	@GeneratedValue(generator = "provider-uuid")
	@GenericGenerator(name = "provider-uuid", strategy = "com.cardinalhealth.chh.config.ProviderUUIDGenerator")
	@Column(name = "HCP_HIST_ID")
	private UUID hcpHistId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HCP_ID")
	private HealthCareProvider hcp;

	@Column(name = "NPI_NUM")
	private Long npiId;

	@Column(name = "FIRT_NAM")
	private String firstName;

	@Column(name = "MIDDLE_NAM")
	private String middleName;

	@Column(name = "LAST_NAM")
	private String lastName;

	@Column(name = "PREFIX_TXT")
	private String prefix;

	@Column(name = "SUFFIX_TXT")
	private String suffix;

	@Column(name = "VRFY_FLG")
	private Boolean verifyStatus;

	@Column(name = "NPI_VRFY_DTE")
	private Timestamp npiVerifyDate;

	@Column(name = "NPI_SRC_FLG")
	private Boolean npiSrcStatus;

	@Column(name = "IS_PICOS_VALID_FLG")
	private Boolean picosValid;

	@Column(name = "UPIN_TXT")
	private String upin;

	@Column(name = "IS_PRESCRIBER_FLG")
	private Boolean prescriberFlag;

	@Column(name = "SEND_DWO_REQ_FLG")
	private Boolean sendDWOReqFlag;

	@Column(name = "SEND_MED_DOC_REQ_FLG")
	private Boolean sendMedoDocReqFlag;

	@Column(name = "START_STP")
	private Timestamp startStp;

	@Column(name = "END_STP")
	private Timestamp endStp;

	@Column(name = "CREATE_BY_NAM")
	private String createdByUser;

	@Column(name = "UPDATE_BY_NAM")
	private String updateByUser;

	/**
	 * @return the hcpHistId
	 */
	public UUID getHcpHistId() {
		return hcpHistId;
	}

	/**
	 * @param hcpHistId the hcpHistId to set
	 */
	public void setHcpHistId(UUID hcpHistId) {
		this.hcpHistId = hcpHistId;
	}

	/**
	 * @return the npiId
	 */
	public Long getNpiId() {
		return npiId;
	}

	/**
	 * @param npiId the npiId to set
	 */
	public void setNpiId(Long npiId) {
		this.npiId = npiId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * @return the verifyStatus
	 */
	public Boolean getVerifyStatus() {
		return verifyStatus;
	}

	/**
	 * @param verifyStatus the verifyStatus to set
	 */
	public void setVerifyStatus(Boolean verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	/**
	 * @return the npiVerifyDate
	 */
	public Timestamp getNpiVerifyDate() {
		return npiVerifyDate;
	}

	/**
	 * @param npiVerifyDate the npiVerifyDate to set
	 */
	public void setNpiVerifyDate(Timestamp npiVerifyDate) {
		this.npiVerifyDate = npiVerifyDate;
	}

	/**
	 * @return the npiSrcStatus
	 */
	public Boolean getNpiSrcStatus() {
		return npiSrcStatus;
	}

	/**
	 * @param npiSrcStatus the npiSrcStatus to set
	 */
	public void setNpiSrcStatus(Boolean npiSrcStatus) {
		this.npiSrcStatus = npiSrcStatus;
	}

	/**
	 * @return the picosValid
	 */
	public Boolean getPicosValid() {
		return picosValid;
	}

	/**
	 * @param picosValid the picosValid to set
	 */
	public void setPicosValid(Boolean picosValid) {
		this.picosValid = picosValid;
	}

	/**
	 * @return the upin
	 */
	public String getUpin() {
		return upin;
	}

	/**
	 * @param upin the upin to set
	 */
	public void setUpin(String upin) {
		this.upin = upin;
	}

	/**
	 * @return the prescriberFlag
	 */
	public Boolean getPrescriberFlag() {
		return prescriberFlag;
	}

	/**
	 * @param prescriberFlag the prescriberFlag to set
	 */
	public void setPrescriberFlag(Boolean prescriberFlag) {
		this.prescriberFlag = prescriberFlag;
	}

	/**
	 * @return the sendDWOReqFlag
	 */
	public Boolean getSendDWOReqFlag() {
		return sendDWOReqFlag;
	}

	/**
	 * @param sendDWOReqFlag the sendDWOReqFlag to set
	 */
	public void setSendDWOReqFlag(Boolean sendDWOReqFlag) {
		this.sendDWOReqFlag = sendDWOReqFlag;
	}

	/**
	 * @return the sendMedoDocReqFlag
	 */
	public Boolean getSendMedoDocReqFlag() {
		return sendMedoDocReqFlag;
	}

	/**
	 * @param sendMedoDocReqFlag the sendMedoDocReqFlag to set
	 */
	public void setSendMedoDocReqFlag(Boolean sendMedoDocReqFlag) {
		this.sendMedoDocReqFlag = sendMedoDocReqFlag;
	}

	/**
	 * @return the startStp
	 */
	public Timestamp getStartStp() {
		return startStp;
	}

	/**
	 * @param startStp the startStp to set
	 */
	public void setStartStp(Timestamp startStp) {
		this.startStp = startStp;
	}

	/**
	 * @return the endStp
	 */
	public Timestamp getEndStp() {
		return endStp;
	}

	/**
	 * @param endStp the endStp to set
	 */
	public void setEndStp(Timestamp endStp) {
		this.endStp = endStp;
	}

	/**
	 * @return the createdByUser
	 */
	public String getCreatedByUser() {
		return createdByUser;
	}

	/**
	 * @param createdByUser the createdByUser to set
	 */
	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	/**
	 * @return the updateByUser
	 */
	public String getUpdateByUser() {
		return updateByUser;
	}

	/**
	 * @param updateByUser the updateByUser to set
	 */
	public void setUpdateByUser(String updateByUser) {
		this.updateByUser = updateByUser;
	}

	/**
	 * @return the hcp
	 */
	public HealthCareProvider getHcpMaster() {
		return hcp;
	}

	/**
	 * @param hcp the hcp to set
	 */
	public void setHcpMaster(HealthCareProvider hcpMaster) {
		this.hcp = hcpMaster;
	}
	
	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HealthCareProviderHistory [hcpHistId=" + hcpHistId + ", hcp=" + hcp + ", npiId=" + npiId
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", prefix="
				+ prefix + ", suffix=" + suffix + ", verifyStatus=" + verifyStatus + ", npiVerifyDate=" + npiVerifyDate
				+ ", npiSrcStatus=" + npiSrcStatus + ", picosValid=" + picosValid + ", upin=" + upin
				+ ", prescriberFlag=" + prescriberFlag + ", sendDWOReqFlag=" + sendDWOReqFlag + ", sendMedoDocReqFlag="
				+ sendMedoDocReqFlag + ", startStp=" + startStp + ", endStp=" + endStp + ", createdByUser="
				+ createdByUser + ", updateByUser=" + updateByUser + "]";
	}
}