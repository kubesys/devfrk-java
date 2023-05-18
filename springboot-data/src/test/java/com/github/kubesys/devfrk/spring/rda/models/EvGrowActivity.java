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
@Table(name = "ev_grow_activity")
public class EvGrowActivity {
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

    @Column(name = "grade")
    private Integer grade;

    @Size(max = 500)
    @Column(name = "activity_desc", length = 500)
    private String activityDesc;

    @Size(max = 100)
    @Column(name = "activity_name", length = 100)
    private String activityName;

    @Size(max = 50)
    @Column(name = "org_name", length = 50)
    private String orgName;

    @Column(name = "publish_time")
    private OffsetDateTime publishTime;

    @Column(name = "image_count")
    private Integer imageCount;

    @Size(max = 100)
    @Column(name = "locate", length = 100)
    private String locate;

    @Column(name = "clazz_org_id")
    private Long clazzOrgId;

    @Size(max = 50)
    @Column(name = "clazz_org_name", length = 50)
    private String clazzOrgName;

    @Column(name = "student_id")
    private Long studentId;

    @Size(max = 50)
    @Column(name = "student_name", length = 50)
    private String studentName;

    @NotNull
    @Column(name = "auditor_status", nullable = false)
    private Integer auditorStatus;

    @Column(name = "auditor_id")
    private Long auditorId;

    @Size(max = 50)
    @Column(name = "auditor_name", length = 50)
    private String auditorName;

    @Column(name = "auditor_time")
    private OffsetDateTime auditorTime;

    @Size(max = 255)
    @Column(name = "check_opinion")
    private String checkOpinion;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @Column(name = "end_time")
    private OffsetDateTime endTime;

    @Column(name = "target_detail_id")
    private Long targetDetailId;

}