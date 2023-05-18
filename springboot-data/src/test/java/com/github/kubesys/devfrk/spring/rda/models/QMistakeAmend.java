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
@Table(name = "q_mistake_amend")
public class QMistakeAmend {
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

    @NotNull
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Size(max = 255)
    @NotNull
    @Column(name = "student_name", nullable = false)
    private String studentName;

    @NotNull
    @Column(name = "is_amend", nullable = false)
    private Integer isAmend;

    @Size(max = 255)
    @Column(name = "submit_answer")
    private String submitAnswer;

    @Size(max = 255)
    @Column(name = "recon_answer")
    private String reconAnswer;

    @NotNull
    @Column(name = "mistake_record_id", nullable = false)
    private Long mistakeRecordId;

}