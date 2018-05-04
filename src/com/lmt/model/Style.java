package com.lmt.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Style implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2871968197895068917L;
	private String sId;
	private String sDescribe;
	public Style() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Style(String sId, String sDescribe) {
		super();
		this.sId = sId;
		this.sDescribe = sDescribe;
	}
	@Override
	public String toString() {
		return "Style [sId=" + sId + ", sDescribe=" + sDescribe + "]";
	}
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")  
	@GeneratedValue(generator="systemUUID")
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsDescribe() {
		return sDescribe;
	}
	public void setsDescribe(String sDescribe) {
		this.sDescribe = sDescribe;
	}
	
	
	
}
