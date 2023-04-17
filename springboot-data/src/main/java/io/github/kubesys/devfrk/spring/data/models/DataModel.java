/**
 * Copyright (2023, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.devfrk.spring.data.models;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class DataModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4120058801263251973L;
	
	
	@CreatedDate
	private Date createdDate;
	
	@CreatedDate
	private Date updatedDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	
}
