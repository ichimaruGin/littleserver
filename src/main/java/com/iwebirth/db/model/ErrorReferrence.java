package com.iwebirth.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 2015-1-19
 * obd error referrence
 * **/
@Entity
@Table(name="error_referrence")
public class ErrorReferrence {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	@Column(name="ERROR_CODE",nullable=false,unique=true)
	String errorCode;
	
	@Column(name="ERROR_DETAIL",nullable=false)
	String errorDetail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
	
	public ErrorReferrence(){
		
	}
	public ErrorReferrence(String errorCode,String errorDetail){
		this.errorCode = errorCode;
		this.errorDetail = errorDetail;
	}
	
}
