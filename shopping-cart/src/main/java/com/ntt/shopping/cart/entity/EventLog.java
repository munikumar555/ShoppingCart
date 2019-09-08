package com.ntt.shopping.cart.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "EVENT_LOG")
@Builder
public class EventLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="ID")
	private String id;
	
	@Column(name="CART_INFO")
	private String cartInfo;
	
	@Column(name="RETRY_COUNT")
	private int retryCount;
	
	@Column(name="RETRY_TIME")
	private Timestamp timestamp;
	
	@Column(name="RESPONSE")
	private String response;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCartInfo() {
		return cartInfo;
	}

	public void setCartInfo(String cartInfo) {
		this.cartInfo = cartInfo;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}