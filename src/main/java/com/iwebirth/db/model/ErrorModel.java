package com.iwebirth.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * 2015-1-19
 * kinds of traffic replicate the same chart
 * **/
@Entity
@Table(name="error")
public class ErrorModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	@Column(name="TID",nullable=false,columnDefinition="varchar(10) not null")
	String tId;
	
	@OneToOne(targetEntity=ErrorReferrence.class)
	//@Cascade(value={CascadeType.PERSIST}) //去掉级联删除
	@JoinColumn(name="ERROR_ID",referencedColumnName="id",nullable=false)
	ErrorReferrence errorReferrence;
	
	
	@Column(name="UPLOADTIME",nullable=false,columnDefinition="bigint(64) not null")
	Long uploadTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public ErrorReferrence getErrorReferrence() {
		return errorReferrence;
	}

	public void setErrorReferrence(ErrorReferrence errorReferrence) {
		this.errorReferrence = errorReferrence;
	}

	public Long getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Long uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	public ErrorModel(){
		
	}
	public ErrorModel(String tId,ErrorReferrence ref){
		this.tId = tId;
		this.errorReferrence = ref;
		this.uploadTime = System.currentTimeMillis();
	}
}
