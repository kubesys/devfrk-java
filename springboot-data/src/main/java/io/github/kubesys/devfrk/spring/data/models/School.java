/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.data.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "c_school")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class School extends DataModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2238137144618461454L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 15)
	// 学校代码
	private String code;
	
	// 学校名称
	@Column(length = 50)
	private String name;
	
	// 所在城市
	@Column(length = 50)
	private String city;
	
	// 学校地址
	@Column(length = 50)
	private String address;
	
	// 电话号码
	@Column(length = 50)
	private String telnum;
	
	// 传真号码
	@Column(length = 50)
	private String fax;
	
	// 学校简介
	@Column(length = 50)
	private String brif;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelnum() {
		return telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getBrif() {
		return brif;
	}

	public void setBrif(String brif) {
		this.brif = brif;
	}
	
}
