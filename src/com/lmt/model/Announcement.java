package com.lmt.model;

import java.io.Serializable;

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
public class Announcement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8904110862919709396L;
	private String aId;
	private String aDescribe;
	private Users aUId;
	private String aTime;
	private int aSign;
	public Announcement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Announcement(String aId, String aDescribe, Users aUId, String aTime, int aSign) {
		super();
		this.aId = aId;
		this.aDescribe = aDescribe;
		this.aUId = aUId;
		this.aTime = aTime;
		this.aSign = aSign;
	}
	@Override
	public String toString() {
		return "Announcement [aId=" + aId + ", aDescribe=" + aDescribe + ", aUId=" + aUId + ", aTime=" + aTime
				+ ", aSign=" + aSign + "]";
	}
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")  
	@GeneratedValue(generator="systemUUID")
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaDescribe() {
		return aDescribe;
	}
	public void setaDescribe(String aDescribe) {
		this.aDescribe = aDescribe;
	}
	@ManyToOne
	@JoinColumn(name="aUId")
	public Users getaUId() {
		return aUId;
	}
	public void setaUId(Users aUId) {
		this.aUId = aUId;
	}
	public String getaTime() {
		return aTime;
	}
	public void setaTime(String aTime) {
		this.aTime = aTime;
	}
	public int getaSign() {
		return aSign;
	}
	public void setaSign(int aSign) {
		this.aSign = aSign;
	}
	
	
}
