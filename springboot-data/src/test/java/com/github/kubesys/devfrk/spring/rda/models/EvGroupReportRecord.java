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
@Table(name = "ev_group_report_record")
public class EvGroupReportRecord {
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

    @NotNull
    @Column(name = "average_score", nullable = false)
    private Double averageScore;

    @NotNull
    @Column(name = "weight_type", nullable = false)
    private Long weightType;

}