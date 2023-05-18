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
@Table(name = "ev_student_evaluation")
public class EvStudentEvaluation {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "student_num", length = 50)
    private String studentNum;

    @Column(name = "enrollment_year")
    private Integer enrollmentYear;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @Size(max = 500)
    @Column(name = "\"desc\"", length = 500)
    private String desc;

    @Column(name = "score")
    private Double score;

    @NotNull
    @Column(name = "is_positive", nullable = false)
    private Boolean isPositive = false;

    @NotNull
    @Column(name = "file_count", nullable = false)
    private Integer fileCount;

    @Column(name = "publisher_id")
    private Long publisherId;

    @Size(max = 50)
    @Column(name = "publisher_name", length = 50)
    private String publisherName;

    @NotNull
    @Column(name = "publish_time", nullable = false)
    private OffsetDateTime publishTime;

    @Column(name = "auditor_id")
    private Long auditorId;

    @Size(max = 50)
    @Column(name = "auditor_name", length = 50)
    private String auditorName;

    @Column(name = "auditor_time")
    private OffsetDateTime auditorTime;

    @Column(name = "group_org_id")
    private Long groupOrgId;

    @Size(max = 50)
    @Column(name = "group_org_name", length = 50)
    private String groupOrgName;

    @Column(name = "student_id")
    private Long studentId;

    @Size(max = 50)
    @Column(name = "student_name", length = 50)
    private String studentName;

    @Column(name = "clazz_org_id")
    private Long clazzOrgId;

    @Size(max = 50)
    @Column(name = "clazz_org_name", length = 50)
    private String clazzOrgName;

    @Column(name = "essential_id")
    private Long essentialId;

    @Column(name = "score_by_comment_id")
    private Long scoreByCommentId;

    @Column(name = "semester_id")
    private Long semesterId;

    @Size(max = 50)
    @Column(name = "create_name", length = 50)
    private String createName;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = false;

    @Size(max = 50)
    @Column(name = "update_name", length = 50)
    private String updateName;

    @Column(name = "update_time")
    private OffsetDateTime updateTime;

    @Column(name = "tape_id")
    private Long tapeId;

}