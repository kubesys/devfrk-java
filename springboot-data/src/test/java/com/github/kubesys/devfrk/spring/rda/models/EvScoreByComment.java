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
@Table(name = "ev_score_by_comment")
public class EvScoreByComment {
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

    @Size(max = 500)
    @Column(name = "comment", length = 500)
    private String comment;

    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @Column(name = "score")
    private Integer score;

    @NotNull
    @Column(name = "is_positive", nullable = false)
    private Boolean isPositive = false;

    @NotNull
    @Column(name = "essential_id", nullable = false)
    private Long essentialId;

}