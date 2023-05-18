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
@Table(name = "ev_evaluation_rule")
public class EvEvaluationRule {
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
    @Column(name = "er_type", nullable = false)
    private Integer erType;

    @Column(name = "code")
    private Integer code;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "src", nullable = false)
    private String src;

    @Column(name = "\"desc\"", length = Integer.MAX_VALUE)
    private String desc;

    @Column(name = "clazz_org_id")
    private Long clazzOrgId;

    @Size(max = 50)
    @Column(name = "clazz_org_name", length = 50)
    private String clazzOrgName;

    @Size(max = 32)
    @Column(name = "school_org_id", length = 32)
    private String schoolOrgId;

    @Size(max = 50)
    @Column(name = "school_org_name", length = 50)
    private String schoolOrgName;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status = false;

    @Column(name = "scheme_id")
    private Long schemeId;

}