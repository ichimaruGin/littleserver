package com.iwebirth.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 2015-1-19
 * kinds of traffic replicate the same chart
 * **/
@Entity
@Table(name="runinfo")
public class RunInfoModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	@Column(name="TID",nullable=false,updatable=false,columnDefinition="varchar(10) not null")
	String tId;
	
	@Column(name="SPEED",nullable=false,columnDefinition="int(11) not null")
	Integer speed;
	
	@Column(name="ROTATE_SPEED",nullable=false,columnDefinition="int(11) not null")
	Integer rotateSpeed;
	
	@Column(name="WATER_TEMPERATURE",nullable=false,columnDefinition="int(11) not null")
	Integer waterTemperature;
	
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

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getRotateSpeed() {
		return rotateSpeed;
	}

	public void setRotateSpeed(Integer rotateSpeed) {
		this.rotateSpeed = rotateSpeed;
	}

	public Integer getWaterTemperature() {
		return waterTemperature;
	}

	public void setWaterTemperature(Integer waterTemperature) {
		this.waterTemperature = waterTemperature;
	}

	public Long getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Long uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	public RunInfoModel(String tId,Integer speed,Integer rotateSpeed,Integer waterTemperatue){
		this.tId = tId;
		this.speed = speed;
		this.rotateSpeed = rotateSpeed;
		this.waterTemperature = waterTemperatue;
		this.uploadTime = System.currentTimeMillis();		
	}
	
}
