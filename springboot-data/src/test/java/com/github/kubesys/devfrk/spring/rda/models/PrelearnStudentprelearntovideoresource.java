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
@Table(name = "prelearn_studentprelearntovideoresource")
public class PrelearnStudentprelearntovideoresource {
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
    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    @Column(name = "look_time")
    private OffsetDateTime lookTime;

    @NotNull
    @Column(name = "student_prelearn_task_id", nullable = false)
    private Long studentPrelearnTaskId;

    @NotNull
    @Column(name = "video_resource_id", nullable = false)
    private Long videoResourceId;

}