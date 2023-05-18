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
@Table(name = "homework_single_record")
public class HomeworkSingleRecord {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @NotNull
    @Column(name = "is_submitted", nullable = false)
    private Boolean isSubmitted = false;

    @Size(max = 255)
    @Column(name = "draft")
    private String draft;

    @Column(name = "review")
    private byte[] review;

    @Size(max = 50)
    @Column(name = "check_name", length = 50)
    private String checkName;

    @NotNull
    @Column(name = "is_checked", nullable = false)
    private Boolean isChecked = false;

    @Size(max = 15)
    @Column(name = "check_result", length = 15)
    private String checkResult;

    @Size(max = 255)
    @Column(name = "recon_answer")
    private String reconAnswer;

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

    @Column(name = "active_time")
    private OffsetDateTime activeTime;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = false;

    @Column(name = "homework_student_id")
    private Long homeworkStudentId;

    @NotNull
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Size(max = 255)
    @Column(name = "pc_checks")
    private String pcChecks;

    @Column(name = "score")
    private Double score;

}