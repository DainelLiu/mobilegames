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
public class Picture implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8364319449645007823L;
	private String pId;
	private String pDescribe;
	private Goods pGId;
	public Picture() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Picture(String pId, String pDescribe, Goods pGId) {
		super();
		this.pId = pId;
		this.pDescribe = pDescribe;
		this.pGId = pGId;
	}
	@Override
	public String toString() {
		return "Picture [pId=" + pId + ", pDescribe=" + pDescribe + ", pGId=" + pGId + "]";
	}
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getpDescribe() {
		return pDescribe;
	}
	public void setpDescribe(String pDescribe) {
		this.pDescribe = pDescribe;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="pGId")
	public Goods getpGId() {
		return pGId;
	}
	public void setpGId(Goods pGId) {
		this.pGId = pGId;
	}
	
	
	
	
}
