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
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5076386388579772898L;
	private String commId;
	private String commDescribe;
	private Users commUId;
	private Users commToUId;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(String commId, String commDescribe, Users commUId, Users commToUId) {
		super();
		this.commId = commId;
		this.commDescribe = commDescribe;
		this.commUId = commUId;
		this.commToUId = commToUId;
	}
	@Override
	public String toString() {
		return "Comment [commId=" + commId + ", commDescribe=" + commDescribe + ", commUId=" + commUId + ", commToUId="
				+ commToUId + "]";
	}
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")  
	@GeneratedValue(generator="systemUUID")
	public String getcommId() {
		return commId;
	}
	public void setcommId(String commId) {
		this.commId = commId;
	}
	public String getcommDescribe() {
		return commDescribe;
	}
	public void setcommDescribe(String commDescribe) {
		this.commDescribe = commDescribe;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="commUId")
	public Users getcommUId() {
		return commUId;
	}
	public void setcommUId(Users commUId) {
		this.commUId = commUId;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="commToUId")
	public Users getcommToUId() {
		return commToUId;
	}
	public void setcommToUId(Users commToUId) {
		this.commToUId = commToUId;
	}
	
	
	
	
}
