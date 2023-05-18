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
@Table(name = "ev_complaint_activity")
public class EvComplaintActivity {
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

    @Column(name = "image_count")
    private Integer imageCount;

    @Size(max = 255)
    @Column(name = "appeal_reason")
    private String appealReason;

    @Size(max = 255)
    @Column(name = "check_opinion")
    private String checkOpinion;

    @NotNull
    @Column(name = "auditor_status", nullable = false)
    private Integer auditorStatus;

    @Column(name = "student_id")
    private Long studentId;

    @Size(max = 50)
    @Column(name = "student_name", length = 50)
    private String studentName;

    @Column(name = "auditor_id")
    private Long auditorId;

    @Size(max = 50)
    @Column(name = "auditor_name", length = 50)
    private String auditorName;

    @Column(name = "grow_activity_id")
    private Long growActivityId;

}