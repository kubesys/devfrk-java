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
@Table(name = "p_q_relation")
public class PQRelation {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "point_id", nullable = false)
    private Long pointId;

    @NotNull
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Size(max = 127)
    @Column(name = "point_name", length = 127)
    private String pointName;

    @Size(max = 50)
    @Column(name = "create_name", length = 50)
    private String createName;

    @Size(max = 50)
    @Column(name = "update_name", length = 50)
    private String updateName;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private OffsetDateTime updateTime;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = false;

}