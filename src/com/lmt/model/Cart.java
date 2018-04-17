package com.lmt.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8364319449645007823L;
	private String cId;
	private Goods cGId;
	private Users cUId;
	private int cNumber;
	private BigDecimal cSubtotal;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(String cId, Goods cGId, Users cUId, int cNumber, BigDecimal cSubtotal) {
		super();
		this.cId = cId;
		this.cGId = cGId;
		this.cUId = cUId;
		this.cNumber = cNumber;
		this.cSubtotal = cSubtotal;
	}
	@Override
	public String toString() {
		return "Cart [cId=" + cId + ", cGId=" + cGId + ", cUId=" + cUId + ", cNumber=" + cNumber + ", cSubtotal="
				+ cSubtotal + "]";
	}
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")  
	@GeneratedValue(generator="systemUUID")
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	@OneToOne
	@JoinColumn(name="cGId")
	public Goods getcGId() {
		return cGId;
	}
	public void setcGId(Goods cGId) {
		this.cGId = cGId;
	}
	@OneToOne
	@JoinColumn(name="cUId")
	public Users getcUId() {
		return cUId;
	}
	public void setcUId(Users cUId) {
		this.cUId = cUId;
	}
	public int getcNumber() {
		return cNumber;
	}
	public void setcNumber(int cNumber) {
		this.cNumber = cNumber;
	}
	public BigDecimal getcSubtotal() {
		return cSubtotal;
	}
	public void setcSubtotal(BigDecimal cSubtotal) {
		this.cSubtotal = cSubtotal;
	}
	
	
	
		
}
