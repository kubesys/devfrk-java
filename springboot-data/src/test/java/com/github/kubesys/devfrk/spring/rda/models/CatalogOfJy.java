package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "catalog_of_jy")
public class CatalogOfJy {
    @Id
    @Size(max = 255)
    @Column(name = "cid", nullable = false)
    private String id;

    @Size(max = 255)
    @NotNull
    @Column(name = "seq", nullable = false)
    private String seq;

    @Size(max = 255)
    @NotNull
    @Column(name = "level", nullable = false)
    private String level;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "catalog_id")
    private Long catalogId;

}