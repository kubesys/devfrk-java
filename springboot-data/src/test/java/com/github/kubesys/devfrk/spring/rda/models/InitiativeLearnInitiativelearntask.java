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
@Table(name = "initiative_learn_initiativelearntask")
public class InitiativeLearnInitiativelearntask {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "create_name", length = 50)
    private String createName;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    @Size(max = 50)
    @Column(name = "update_name", length = 50)
    private String updateName;

    @Column(name = "update_time")
    private OffsetDateTime updateTime;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = false;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Size(max = 50)
    @Column(name = "teacher_name", length = 50)
    private String teacherName;

    @NotNull
    @Column(name = "catalog_seq", nullable = false)
    private Integer catalogSeq;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "catalog_id")
    private Long catalogId;

    @Column(name = "ebook_catalog_id")
    private Long ebookCatalogId;

    @Column(name = "edition_id")
    private Long editionId;

    @Column(name = "paper_id")
    private Long paperId;

    @Column(name = "subject_id")
    private Long subjectId;

}