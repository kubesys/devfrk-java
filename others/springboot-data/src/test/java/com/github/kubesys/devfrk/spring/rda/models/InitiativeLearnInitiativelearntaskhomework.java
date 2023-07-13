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
@Table(name = "initiative_learn_initiativelearntaskhomework")
public class InitiativeLearnInitiativelearntaskhomework {
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
    @Column(name = "answers")
    private String answers;

    @Size(max = 255)
    @Column(name = "draft")
    private String draft;

    @Size(max = 255)
    @Column(name = "recon_answer")
    private String reconAnswer;

    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @Column(name = "check_result")
    private Integer checkResult;

    @Size(max = 50)
    @Column(name = "check_name", length = 50)
    private String checkName;

    @Column(name = "check_time")
    private OffsetDateTime checkTime;

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

    @Column(name = "initiative_learn_task_record_id")
    private Long initiativeLearnTaskRecordId;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "used_time")
    private Integer usedTime;

}