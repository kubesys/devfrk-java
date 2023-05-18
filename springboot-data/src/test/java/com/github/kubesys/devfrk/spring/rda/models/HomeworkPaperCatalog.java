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
@Table(name = "homework_paper_catalog")
public class HomeworkPaperCatalog {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "paper_id", nullable = false)
    private Long paperId;

    @NotNull
    @Column(name = "catalog_id", nullable = false)
    private Long catalogId;

}