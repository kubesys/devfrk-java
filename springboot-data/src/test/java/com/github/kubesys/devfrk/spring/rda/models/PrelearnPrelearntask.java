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
@Table(name = "prelearn_prelearntask")
public class PrelearnPrelearntask {
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

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull
    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    private String content;

    @Column(name = "phrase_year")
    private Integer phraseYear;

    @Column(name = "phrase_study")
    private Integer phraseStudy;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "clazz_id")
    private Long clazzId;

    @Size(max = 50)
    @Column(name = "clazz_name", length = 50)
    private String clazzName;

    @Column(name = "school_org_id")
    private Long schoolOrgId;

    @Size(max = 50)
    @Column(name = "school_org_name", length = 50)
    private String schoolOrgName;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @Column(name = "end_time")
    private OffsetDateTime endTime;

    @NotNull
    @Column(name = "publish_time", nullable = false)
    private OffsetDateTime publishTime;

    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "ebook_id")
    private Long ebookId;

    @Column(name = "ebook_catalog_id")
    private Long ebookCatalogId;

    @Column(name = "edition_id")
    private Long editionId;

    @Column(name = "homework_id")
    private Long homeworkId;

    @Column(name = "paper_id")
    private Long paperId;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "enrollment_year")
    private Integer enrollmentYear;

    @NotNull
    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Size(max = 64)
    @Column(name = "teacher_name", length = 64)
    private String teacherName;

    @Size(max = 128)
    @NotNull
    @Column(name = "teacher_username", nullable = false, length = 128)
    private String teacherUsername;

}