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
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "c_subject")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Subject extends DataModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2238137144618461454L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 书本名
	@PrimaryKeyJoinColumn
	@Column(length = 50)
	private String name;
}
