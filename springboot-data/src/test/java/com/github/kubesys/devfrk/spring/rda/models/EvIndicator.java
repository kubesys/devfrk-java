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
@Table(name = "ev_indicator")
public class EvIndicator {
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

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 500)
    @Column(name = "\"desc\"", length = 500)
    private String desc;

    @Column(name = "weight")
    private Double weight;

    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "scheme_id")
    private Long schemeId;

    @NotNull
    @Column(name = "level_id", nullable = false)
    private Long levelId;

}