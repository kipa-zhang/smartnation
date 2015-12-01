package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

@Service("businfo")
@Entity
@Table(name = "businfo")
public class BusInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "busInfoId", unique = true, nullable = false)
	private int busInfoId;
	
	/**
	 * The bus service number
	 */
	@Column(name = "SI_SVC_NUM")
	private String SI_SVC_NUM;
	
	/**
	 * Transport Operator Code
	 */
	@Column(name = "transportOperatorCode")
	private String transportOperatorCode;
	
	/**
	 * The direction in which the bus travels (1 or 2), loop services only have 1 direction
	 */
	@Column(name = "SI_SVC_DIR")
	private String SI_SVC_DIR;
	
	/**
	 * Category of the SBS bus service: EXPRESS, FEEDER, INDUSTRIAL, TOWNLINK, TRUNK, 2 TIER FLAT FEE, FLAT FEE $1.10 (or $1.90, $3.50, $3.80)
	 */
	@Column(name = "SI_SVC_CAT")
	private String SI_SVC_CAT;
	
	/**
	 * Bus stop code for starting location
	 */
	@Column(name = "SI_BS_CODE_ST")
	private String SI_BS_CODE_ST;
	
	/**
	 * Bus stop code for ending location (similar as starting for loop services)
	 */
	@Column(name = "SI_BS_CODE_END")
	private String SI_BS_CODE_END;
	
	/**
	 * Freq of dispatch for AM Peak 0630H - 0830H (range in minutes)
	 */
	@Column(name = "SI_FREQ_AM_PK")
	private String SI_FREQ_AM_PK;
	
	/**
	 * Freq of dispatch for AM Off-Peak 0831H - 1659H (range in minutes)
	 */
	@Column(name = "SI_FREQ_AM_OF")
	private String SI_FREQ_AM_OF;
	
	/**
	 * Freq of dispatch for PM Peak 1700H - 1900H (range in minutes)
	 */
	@Column(name = "SI_FREQ_PM_PK")
	private String SI_FREQ_PM_PK;
	
	/**
	 * Freq of dispatch for PM Off-Peak after 1900H (range in minutes)
	 */
	@Column(name = "SI_FREQ_PM_OF")
	private String SI_FREQ_PM_OF;
	
	/**
	 * Location at which the bus service loops, empty if not a loop service
	 */
	@Column(name = "SI_LOOP")
	private String SI_LOOP;

	public int getBusInfoId() {
		return busInfoId;
	}

	public void setBusInfoId(int busInfoId) {
		this.busInfoId = busInfoId;
	}

	public String getSI_SVC_NUM() {
		return SI_SVC_NUM;
	}

	public void setSI_SVC_NUM(String sI_SVC_NUM) {
		SI_SVC_NUM = sI_SVC_NUM;
	}

	public String getSI_SVC_DIR() {
		return SI_SVC_DIR;
	}

	public void setSI_SVC_DIR(String sI_SVC_DIR) {
		SI_SVC_DIR = sI_SVC_DIR;
	}

	public String getSI_SVC_CAT() {
		return SI_SVC_CAT;
	}

	public void setSI_SVC_CAT(String sI_SVC_CAT) {
		SI_SVC_CAT = sI_SVC_CAT;
	}

	public String getSI_BS_CODE_ST() {
		return SI_BS_CODE_ST;
	}

	public void setSI_BS_CODE_ST(String sI_BS_CODE_ST) {
		SI_BS_CODE_ST = sI_BS_CODE_ST;
	}

	public String getSI_BS_CODE_END() {
		return SI_BS_CODE_END;
	}

	public void setSI_BS_CODE_END(String sI_BS_CODE_END) {
		SI_BS_CODE_END = sI_BS_CODE_END;
	}

	public String getSI_FREQ_AM_PK() {
		return SI_FREQ_AM_PK;
	}

	public void setSI_FREQ_AM_PK(String sI_FREQ_AM_PK) {
		SI_FREQ_AM_PK = sI_FREQ_AM_PK;
	}

	public String getSI_FREQ_AM_OF() {
		return SI_FREQ_AM_OF;
	}

	public void setSI_FREQ_AM_OF(String sI_FREQ_AM_OF) {
		SI_FREQ_AM_OF = sI_FREQ_AM_OF;
	}

	public String getSI_FREQ_PM_PK() {
		return SI_FREQ_PM_PK;
	}

	public void setSI_FREQ_PM_PK(String sI_FREQ_PM_PK) {
		SI_FREQ_PM_PK = sI_FREQ_PM_PK;
	}

	public String getSI_FREQ_PM_OF() {
		return SI_FREQ_PM_OF;
	}

	public void setSI_FREQ_PM_OF(String sI_FREQ_PM_OF) {
		SI_FREQ_PM_OF = sI_FREQ_PM_OF;
	}

	public String getSI_LOOP() {
		return SI_LOOP;
	}

	public void setSI_LOOP(String sI_LOOP) {
		SI_LOOP = sI_LOOP;
	}

	public String getTransportOperatorCode() {
		return transportOperatorCode;
	}

	public void setTransportOperatorCode(String transportOperatorCode) {
		this.transportOperatorCode = transportOperatorCode;
	}
	
	
}
