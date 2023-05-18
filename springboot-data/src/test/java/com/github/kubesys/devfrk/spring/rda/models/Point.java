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
@Table(name = "point")
public class Point {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    @Size(max = 127)
    @NotNull
    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @NotNull
    @Column(name = "is_top", nullable = false)
    private Boolean isTop = false;

    @Column(name = "content", length = Integer.MAX_VALUE)
    private String content;

    @Size(max = 50)
    @NotNull
    @Column(name = "create_name", nullable = false, length = 50)
    private String createName;

    @Size(max = 50)
    @NotNull
    @Column(name = "update_name", nullable = false, length = 50)
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