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
@Table(name = "prelearn_studentprelearntask")
public class PrelearnStudentprelearntask {
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
    @Column(name = "name", length = 50)
    private String name;

    @NotNull
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Size(max = 50)
    @NotNull
    @Column(name = "student_name", nullable = false, length = 50)
    private String studentName;

    @NotNull
    @Column(name = "clazz_id", nullable = false)
    private Long clazzId;

    @Size(max = 50)
    @NotNull
    @Column(name = "clazz_name", nullable = false, length = 50)
    private String clazzName;

    @Column(name = "school_org_id")
    private Long schoolOrgId;

    @Size(max = 50)
    @Column(name = "school_org_name", length = 50)
    private String schoolOrgName;

    @Column(name = "phrase_year")
    private Integer phraseYear;

    @Column(name = "phrase_study")
    private Integer phraseStudy;

    @Column(name = "grade")
    private Integer grade;

    @NotNull
    @Column(name = "publish_time", nullable = false)
    private OffsetDateTime publishTime;

    @Column(name = "look_time")
    private OffsetDateTime lookTime;

    @Column(name = "submit_time")
    private OffsetDateTime submitTime;

    @Column(name = "is_submit")
    private Boolean isSubmit;

    @Column(name = "marks_path", length = Integer.MAX_VALUE)
    private String marksPath;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "ebook_id")
    private Long ebookId;

    @Column(name = "ebook_catalog_id")
    private Long ebookCatalogId;

    @Column(name = "edition_id")
    private Long editionId;

    @Column(name = "homework_student_id")
    private Long homeworkStudentId;

    @NotNull
    @Column(name = "pre_learn_task_id", nullable = false)
    private Long preLearnTaskId;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "enrollment_year")
    private Integer enrollmentYear;

    @NotNull
    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    @Column(name = "dateline")
    private OffsetDateTime dateline;

}