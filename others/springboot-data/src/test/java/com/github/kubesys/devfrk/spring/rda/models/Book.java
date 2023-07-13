package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "grade")
    private Integer grade;

    @Size(max = 50)
    @Column(name = "term", length = 50)
    private String term;

    @Column(name = "\"termID\"")
    private Integer termID;

    @Size(max = 50)
    @NotNull
    @Column(name = "subject", nullable = false, length = 50)
    private String subject;

    @Column(name = "edition_id")
    private Long editionId;

    @Size(max = 50)
    @NotNull
    @Column(name = "create_name", nullable = false, length = 50)
    private String createName;

    @Size(max = 50)
    @NotNull
    @Column(name = "update_name", nullable = false, length = 50)
    private String updateName;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private OffsetDateTime updateTime;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = false;

    @Column(name = "ebook_id")
    private Long ebookId;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

}