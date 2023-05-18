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
@Table(name = "ev_clazz_evaluation")
public class EvClazzEvaluation {
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

    @Column(name = "score")
    private Double score;

    @NotNull
    @Column(name = "is_positive", nullable = false)
    private Boolean isPositive = false;

    @NotNull
    @Column(name = "file_count", nullable = false)
    private Integer fileCount;

    @Size(max = 500)
    @Column(name = "\"desc\"", length = 500)
    private String desc;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "enrollment_year")
    private Integer enrollmentYear;

    @Column(name = "clazz_org_id")
    private Long clazzOrgId;

    @Size(max = 50)
    @Column(name = "clazz_org_name", length = 50)
    private String clazzOrgName;

    @Size(max = 50)
    @Column(name = "publish_name", length = 50)
    private String publishName;

    @Column(name = "publish_id")
    private Long publishId;

    @Column(name = "publish_time")
    private OffsetDateTime publishTime;

    @Size(max = 50)
    @Column(name = "auditor_name", length = 50)
    private String auditorName;

    @Column(name = "auditor_id")
    private Long auditorId;

    @Column(name = "auditor_time")
    private OffsetDateTime auditorTime;

    @Column(name = "essential_id")
    private Long essentialId;

    @Column(name = "score_by_comment_id")
    private Long scoreByCommentId;

    @Column(name = "semester_id")
    private Long semesterId;

}