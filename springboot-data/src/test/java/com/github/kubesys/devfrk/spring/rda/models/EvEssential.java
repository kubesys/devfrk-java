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
@Table(name = "ev_essential")
public class EvEssential {
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
    @Column(name = "name")
    private String name;

    @Size(max = 500)
    @Column(name = "detail", length = 500)
    private String detail;

    @Column(name = "weight")
    private Double weight;

    @NotNull
    @Column(name = "evaluate_method", nullable = false)
    private Integer evaluateMethod;

    @NotNull
    @Column(name = "scoring_type", nullable = false)
    private Integer scoringType;

    @NotNull
    @Column(name = "evaluate_cycle", nullable = false)
    private Integer evaluateCycle;

    @NotNull
    @Column(name = "evaluate_type", nullable = false)
    private Integer evaluateType;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @NotNull
    @Column(name = "scene", nullable = false)
    private Integer scene;

    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @Column(name = "indicator_id")
    private Long indicatorId;

}