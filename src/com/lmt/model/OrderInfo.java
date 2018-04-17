package com.lmt.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class OrderInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8364319449645007823L;
	private String oId;
	private Users oUId;
	private Goods oGId;
	private BigDecimal oTotal;
	private String oDetermine;
	private String oComplete;
	private int oSign;
	public OrderInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderInfo(String oId, Users oUId, Goods oGId, BigDecimal oTotal, String oDetermine, String oComplete,
			int oSign) {
		super();
		this.oId = oId;
		this.oUId = oUId;
		this.oGId = oGId;
		this.oTotal = oTotal;
		this.oDetermine = oDetermine;
		this.oComplete = oComplete;
		this.oSign = oSign;
	}
	@Override
	public String toString() {
		return "OrderInfo [oId=" + oId + ", oUId=" + oUId + ", oGId=" + oGId + ", oTotal=" + oTotal + ", oDetermine="
				+ oDetermine + ", oComplete=" + oComplete + ", oSign=" + oSign + "]";
	}
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")  
	@GeneratedValue(generator="systemUUID")
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="oUId")
	public Users getoUId() {
		return oUId;
	}
	public void setoUId(Users oUId) {
		this.oUId = oUId;
	}
	@OneToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="oGId")
	public Goods getoGId() {
		return oGId;
	}
	public void setoGId(Goods oGId) {
		this.oGId = oGId;
	}
	public BigDecimal getoTotal() {
		return oTotal;
	}
	public void setoTotal(BigDecimal oTotal) {
		this.oTotal = oTotal;
	}
	public String getoDetermine() {
		return oDetermine;
	}
	public void setoDetermine(String oDetermine) {
		this.oDetermine = oDetermine;
	}
	public String getoComplete() {
		return oComplete;
	}
	public void setoComplete(String oComplete) {
		this.oComplete = oComplete;
	}
	public int getoSign() {
		return oSign;
	}
	public void setoSign(int oSign) {
		this.oSign = oSign;
	}
	
	
	
	
	
	
}
