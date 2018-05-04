package com.lmt.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Games implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -574529884716216915L;
	private String gaId;
	private String gaDescribe;
	public Games() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Games(String gaId, String gaDescribe) {
		super();
		this.gaId = gaId;
		this.gaDescribe = gaDescribe;
	}
	@Override
	public String toString() {
		return "Games [gaId=" + gaId + ", gaDescribe=" + gaDescribe + "]";
	}
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")  
	@GeneratedValue(generator="systemUUID")
	public String getgaId() {
		return gaId;
	}
	public void setgaId(String gaId) {
		this.gaId = gaId;
	}
	public String getgaDescribe() {
		return gaDescribe;
	}
	public void setgaDescribe(String gaDescribe) {
		this.gaDescribe = gaDescribe;
	}
	
	
	
	
}
