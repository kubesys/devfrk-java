package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "homework_homework_catalog")
public class HomeworkHomeworkCatalog {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "homework_id", nullable = false)
    private Long homeworkId;

    @NotNull
    @Column(name = "catalog_id", nullable = false)
    private Long catalogId;

}