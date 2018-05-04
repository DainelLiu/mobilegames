package com.lmt.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Collect implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 682462581967875329L;
	private String coId;
	private Goods coGId;
	private Users coUId;
	public Collect() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Collect(String coId, Goods coGId, Users coUId) {
		super();
		this.coId = coId;
		this.coGId = coGId;
		this.coUId = coUId;
	}
	@Override
	public String toString() {
		return "Collect [coId=" + coId + ", coGId=" + coGId + ", coUId=" + coUId + "]";
	}
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")  
	@GeneratedValue(generator="systemUUID")
	public String getcoId() {
		return coId;
	}
	public void setcoId(String coId) {
		this.coId = coId;
	}
	@OneToOne
	@JoinColumn(name="coGId")
	public Goods getcoGId() {
		return coGId;
	}
	public void setcoGId(Goods coGId) {
		this.coGId = coGId;
	}
	@OneToOne
	@JoinColumn(name="coUId")
	public Users getcoUId() {
		return coUId;
	}
	public void setcoUId(Users coUId) {
		this.coUId = coUId;
	}
	
	
	
	
}
