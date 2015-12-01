package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

/**
 * 
 * @author shizhou
 *
 */
@Service("busroute")
@Entity
@Table(name = "busroute")
public class BusRoute {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "busRouteId", unique = true, nullable = false)
	private int busRouteId;
	
	/**
	 * The bus service number
	 */
	@Column(name = "SR_SVC_NUM")
	private String SR_SVC_NUM;
	
	/**
	 * Transport Operator Code
	 */
	@Column(name = "transportOperatorCode")
	private String transportOperatorCode;
	
	/**
	 * The direction in which the bus travels (1 or 2), loop services only have 1 direction
	 */
	@Column(name = "SR_SVC_DIR")
	private String SR_SVC_DIR;
	
	/**
	 * The i-th bus stop for this route
	 */
	@Column(name = "SR_ROUT_SEQ")
	private String SR_ROUT_SEQ;
	
	/**
	 * Bus stop reference code
	 */
	@Column(name = "SR_BS_CODE")
	private String SR_BS_CODE;
	
	/**
	 * Distance travelled by bus from starting location (in kilometres)
	 */
	@Column(name = "SR_DISTANCE")
	private String SR_DISTANCE;
	
	/**
	 * First bus timing, weekday
	 */
	@Column(name = "SR_FST_WD")
	private String SR_FST_WD;
	
	/**
	 * Last bus timing, weekday
	 */
	@Column(name = "SR_LST_WD")
	private String SR_LST_WD;
	
	/**
	 * First bus timing, Saturday
	 */
	@Column(name = "SR_FST_SAT")
	private String SR_FST_SAT;
	
	/**
	 * Last bus timing, Saturday
	 */
	@Column(name = "SR_LST_SAT")
	private String SR_LST_SAT;
	
	/**
	 * First bus timing, Sunday
	 */
	@Column(name = "SR_FST_SUN")
	private String SR_FST_SUN;
	
	/**
	 * Last bus timing, Sunday
	 */
	@Column(name = "SR_LST_SUN")
	private String SR_LST_SUN;
	
	public String getSR_SVC_NUM() {
		return SR_SVC_NUM;
	}
	public void setSR_SVC_NUM(String sR_SVC_NUM) {
		SR_SVC_NUM = sR_SVC_NUM;
	}
	public String getSR_SVC_DIR() {
		return SR_SVC_DIR;
	}
	public void setSR_SVC_DIR(String sR_SVC_DIR) {
		SR_SVC_DIR = sR_SVC_DIR;
	}
	public String getSR_ROUT_SEQ() {
		return SR_ROUT_SEQ;
	}
	public void setSR_ROUT_SEQ(String sR_ROUT_SEQ) {
		SR_ROUT_SEQ = sR_ROUT_SEQ;
	}
	public String getSR_BS_CODE() {
		return SR_BS_CODE;
	}
	public void setSR_BS_CODE(String sR_BS_CODE) {
		SR_BS_CODE = sR_BS_CODE;
	}
	public String getSR_DISTANCE() {
		return SR_DISTANCE;
	}
	public void setSR_DISTANCE(String sR_DISTANCE) {
		SR_DISTANCE = sR_DISTANCE;
	}
	public String getSR_FST_WD() {
		return SR_FST_WD;
	}
	public void setSR_FST_WD(String sR_FST_WD) {
		SR_FST_WD = sR_FST_WD;
	}
	public String getSR_LST_WD() {
		return SR_LST_WD;
	}
	public void setSR_LST_WD(String sR_LST_WD) {
		SR_LST_WD = sR_LST_WD;
	}
	public String getSR_FST_SAT() {
		return SR_FST_SAT;
	}
	public void setSR_FST_SAT(String sR_FST_SAT) {
		SR_FST_SAT = sR_FST_SAT;
	}
	public String getSR_LST_SAT() {
		return SR_LST_SAT;
	}
	public void setSR_LST_SAT(String sR_LST_SAT) {
		SR_LST_SAT = sR_LST_SAT;
	}
	public String getSR_FST_SUN() {
		return SR_FST_SUN;
	}
	public void setSR_FST_SUN(String sR_FST_SUN) {
		SR_FST_SUN = sR_FST_SUN;
	}
	public String getSR_LST_SUN() {
		return SR_LST_SUN;
	}
	public void setSR_LST_SUN(String sR_LST_SUN) {
		SR_LST_SUN = sR_LST_SUN;
	}
	public int getBusRouteId() {
		return busRouteId;
	}
	public void setBusRouteId(int busRouteId) {
		this.busRouteId = busRouteId;
	}
	public String getTransportOperatorCode() {
		return transportOperatorCode;
	}
	public void setTransportOperatorCode(String transportOperatorCode) {
		this.transportOperatorCode = transportOperatorCode;
	}
	
}
