package com.lmt.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Goods implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5354819713429297702L;
	private String gId;
	private String gName;
	private Style gSId;
	private BigDecimal gPrice;
	private String gDescribe;
	private String gSTime;
	private String gOTime;
	private String gDuration;
	private Zone gZId;
	private Users gUId;
	private int gSign;
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Goods(String gId, String gName, Style gSId, BigDecimal gPrice, String gDescribe, String gSTime,
			String gOTime, String gDuration, Zone gZId, Users gUId, int gSign) {
		super();
		this.gId = gId;
		this.gName = gName;
		this.gSId = gSId;
		this.gPrice = gPrice;
		this.gDescribe = gDescribe;
		this.gSTime = gSTime;
		this.gOTime = gOTime;
		this.gDuration = gDuration;
		this.gZId = gZId;
		this.gUId = gUId;
		this.gSign = gSign;
	}
	@Override
	public String toString() {
		return "Goods [gId=" + gId + ", gName=" + gName + ", gSId=" + gSId + ", gPrice=" + gPrice + ", gDescribe="
				+ gDescribe + ", gSTime=" + gSTime + ", gOTime=" + gOTime + ", gDuration=" + gDuration + ", gZId="
				+ gZId + ", gUId=" + gUId + ", gSign=" + gSign + "]";
	}
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")  
	@GeneratedValue(generator="systemUUID")
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="gSId")
	public Style getgSId() {
		return gSId;
	}
	public void setgSId(Style gSId) {
		this.gSId = gSId;
	}
	public BigDecimal getgPrice() {
		return gPrice;
	}
	public void setgPrice(BigDecimal gPrice) {
		this.gPrice = gPrice;
	}
	public String getgDescribe() {
		return gDescribe;
	}
	public void setgDescribe(String gDescribe) {
		this.gDescribe = gDescribe;
	}
	public String getgSTime() {
		return gSTime;
	}
	public void setgSTime(String gSTime) {
		this.gSTime = gSTime;
	}
	public String getgOTime() {
		return gOTime;
	}
	public void setgOTime(String gOTime) {
		this.gOTime = gOTime;
	}
	public String getgDuration() {
		return gDuration;
	}
	public void setgDuration(String gDuration) {
		this.gDuration = gDuration;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="gZId")
	public Zone getgZId() {
		return gZId;
	}
	public void setgZId(Zone gZId) {
		this.gZId = gZId;
	}
	public Users getgUId() {
		return gUId;
	}
	public void setgUId(Users gUId) {
		this.gUId = gUId;
	}
	public int getgSign() {
		return gSign;
	}
	public void setgSign(int gSign) {
		this.gSign = gSign;
	}
	
	
	
}
