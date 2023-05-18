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
@Table(name = "homework_homework_student_relation")
public class HomeworkHomeworkStudentRelation {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Size(max = 64)
    @Column(name = "student_name", length = 64)
    private String studentName;

    @Size(max = 128)
    @NotNull
    @Column(name = "student_username", nullable = false, length = 128)
    private String studentUsername;

    @Size(max = 32)
    @Column(name = "student_number", length = 32)
    private String studentNumber;

    @NotNull
    @Column(name = "is_submitted", nullable = false)
    private Boolean isSubmitted = false;

    @Column(name = "submit_time")
    private OffsetDateTime submitTime;

    @NotNull
    @Column(name = "check_status", nullable = false)
    private Integer checkStatus;

    @Size(max = 50)
    @Column(name = "check_name", length = 50)
    private String checkName;

    @Column(name = "check_time")
    private OffsetDateTime checkTime;

    @Size(max = 255)
    @Column(name = "comment")
    private String comment;

    @Size(max = 50)
    @NotNull
    @Column(name = "create_name", nullable = false, length = 50)
    private String createName;

    @Size(max = 50)
    @Column(name = "update_name", length = 50)
    private String updateName;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private OffsetDateTime updateTime;

    @Column(name = "used_time")
    private Integer usedTime;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = false;

    @NotNull
    @Column(name = "rewrite_count", nullable = false)
    private Integer rewriteCount;

    @Column(name = "rewrite_time")
    private OffsetDateTime rewriteTime;

    @NotNull
    @Column(name = "recheck_count", nullable = false)
    private Integer recheckCount;

    @Column(name = "recheck_time")
    private OffsetDateTime recheckTime;

    @NotNull
    @Column(name = "is_excellent", nullable = false)
    private Boolean isExcellent = false;

    @NotNull
    @Column(name = "is_auto", nullable = false)
    private Boolean isAuto = false;

    @NotNull
    @Column(name = "homework_id", nullable = false)
    private Long homeworkId;

    @NotNull
    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    @Column(name = "dateline")
    private OffsetDateTime dateline;

}