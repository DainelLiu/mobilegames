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
public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6822436601399164463L;
	private String uId;
	private String uName;
	private String uPassword;
	private String uPhone;
	private String uMail;
	private String uPicture;
	private BigDecimal uFraction;
	private int uGrade;
	private String uPower;
	private int uSign;
	private BigDecimal uMonery;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(String uId, String uName, String uPassword, String uPhone, String uMail, String uPicture,
			BigDecimal uFraction, int uGrade, String uPower, int uSign, BigDecimal uMonery) {
		super();
		this.uId = uId;
		this.uName = uName;
		this.uPassword = uPassword;
		this.uPhone = uPhone;
		this.uMail = uMail;
		this.uPicture = uPicture;
		this.uFraction = uFraction;
		this.uGrade = uGrade;
		this.uPower = uPower;
		this.uSign = uSign;
		this.uMonery = uMonery;
	}
	@Override
	public String toString() {
		return "Users [uId=" + uId + ", uName=" + uName + ", uPassword=" + uPassword + ", uPhone=" + uPhone + ", uMail="
				+ uMail + ", uPictuer=" + uPicture + ", uFraction=" + uFraction + ", uGrade=" + uGrade + ", uPower="
				+ uPower + ", uSign=" + uSign + ", uMonery=" + uMonery + "]";
	}
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	public String getuMail() {
		return uMail;
	}
	public void setuMail(String uMail) {
		this.uMail = uMail;
	}
	public String getuPicture() {
		return uPicture;
	}
	public void setuPictuer(String uPicture) {
		this.uPicture = uPicture;
	}
	public BigDecimal getuFraction() {
		return uFraction;
	}
	public void setuFraction(BigDecimal uFraction) {
		this.uFraction = uFraction;
	}
	public int getuGrade() {
		return uGrade;
	}
	public void setuGrade(int uGrade) {
		this.uGrade = uGrade;
	}
	public String getuPower() {
		return uPower;
	}
	public void setuPower(String uPower) {
		this.uPower = uPower;
	}
	public int getuSign() {
		return uSign;
	}
	public void setuSign(int uSign) {
		this.uSign = uSign;
	}
	public BigDecimal getuMonery() {
		return uMonery;
	}
	public void setuMonery(BigDecimal uMonery) {
		this.uMonery = uMonery;
	}
	
	
	
	
}
