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
@Table(name = "rs_paper_template")
public class RsPaperTemplate {
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

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Column(name = "question_count")
    private Integer questionCount;

    @NotNull
    @Column(name = "publish_count", nullable = false)
    private Integer publishCount;

    @NotNull
    @Column(name = "school_org_id", nullable = false)
    private Long schoolOrgId;

    @Size(max = 64)
    @NotNull
    @Column(name = "school_org_name", nullable = false, length = 64)
    private String schoolOrgName;

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

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "catalog_id")
    private Long catalogId;

    @Column(name = "edition_id")
    private Long editionId;

    @Column(name = "subject_id")
    private Long subjectId;

}