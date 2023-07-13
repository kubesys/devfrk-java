package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "ev_student_report_record")
public class EvStudentReportRecord {
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
    @Column(name = "clazz_org_id", nullable = false)
    private Long clazzOrgId;

    @Size(max = 50)
    @NotNull
    @Column(name = "clazz_org_name", nullable = false, length = 50)
    private String clazzOrgName;

    @NotNull
    @Column(name = "group_org_id", nullable = false)
    private Long groupOrgId;

    @Size(max = 50)
    @NotNull
    @Column(name = "group_org_name", nullable = false, length = 50)
    private String groupOrgName;

    @NotNull
    @Column(name = "rank", nullable = false)
    private Integer rank;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "source_score", nullable = false)
    private Double sourceScore;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "weight_score")
    private Double weightScore;

    @NotNull
    @Column(name = "evaluate_cycle", nullable = false)
    private Integer evaluateCycle;

    @Column(name = "indicator_data", length = Integer.MAX_VALUE)
    private String indicatorData;

    @Column(name = "student_id")
    private Long studentId;

    @Size(max = 50)
    @Column(name = "student_name", length = 50)
    private String studentName;

    @NotNull
    @Column(name = "group_weight_score", nullable = false)
    private Double groupWeightScore;

    @NotNull
    @Column(name = "clazz_weight", nullable = false)
    private Double clazzWeight;

    @NotNull
    @Column(name = "clazz_weight_score", nullable = false)
    private Double clazzWeightScore;

    @NotNull
    @Column(name = "group_weight", nullable = false)
    private Double groupWeight;

    @Column(name = "weight_type")
    private Long weightType;

    @NotNull
    @Column(name = "reviews", nullable = false)
    private Integer reviews;

    @NotNull
    @Column(name = "is_techer", nullable = false)
    private Boolean isTecher = false;

}