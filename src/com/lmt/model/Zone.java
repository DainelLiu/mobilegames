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
public class Zone implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8364319449645007823L;
	private String zId;
	private String zDescribe;
	private Games zGaId;
	public Zone() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Zone(String zId, String zDescribe, Games zGaId) {
		super();
		this.zId = zId;
		this.zDescribe = zDescribe;
		this.zGaId = zGaId;
	}
	@Override
	public String toString() {
		return "Zone [zId=" + zId + ", zDescribe=" + zDescribe + ", zGaId=" + zGaId + "]";
	}
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	public String getzId() {
		return zId;
	}
	public void setzId(String zId) {
		this.zId = zId;
	}
	public String getzDescribe() {
		return zDescribe;
	}
	public void setzDescribe(String zDescribe) {
		this.zDescribe = zDescribe;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="zGaId")
	public Games getzGaId() {
		return zGaId;
	}
	public void setzGaId(Games zGaId) {
		this.zGaId = zGaId;
	}
	
	
	
	
}
