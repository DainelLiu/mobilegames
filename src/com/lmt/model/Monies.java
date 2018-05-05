package com.lmt.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Monies implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6001127446648256364L;
	
	private String mId;
	private String mTime;
	private BigDecimal mChange;
	public Monies() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Monies(String mId, String mTime, BigDecimal mChange) {
		super();
		this.mId = mId;
		this.mTime = mTime;
		this.mChange = mChange;
		}


	
	

	@Override
	public String toString() {
		return "Monies [mId=" + mId + ", mTime=" + mTime + ", mChange=" + mChange + "]";
	}


	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")  
	@GeneratedValue(generator="systemUUID")
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmTime() {
		return mTime;
	}
	public void setmTime(String mTime) {
		this.mTime = mTime;
	}


	public BigDecimal getmChange() {
		return mChange;
	}


	public void setmChange(BigDecimal mChange) {
		this.mChange = mChange;
	}


	
	
	
}
