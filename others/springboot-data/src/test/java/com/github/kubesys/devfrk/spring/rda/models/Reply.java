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
@Table(name = "reply")
public class Reply {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 2048)
    @NotNull
    @Column(name = "content", nullable = false, length = 2048)
    private String content;

    @NotNull
    @Column(name = "satisfaction_value", nullable = false)
    private Integer satisfactionValue;

    @Size(max = 1024)
    @Column(name = "reason", length = 1024)
    private String reason;

    @Size(max = 32)
    @NotNull
    @Column(name = "create_name", nullable = false, length = 32)
    private String createName;

    @Size(max = 32)
    @NotNull
    @Column(name = "update_name", nullable = false, length = 32)
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

    @NotNull
    @Column(name = "complaint_id", nullable = false)
    private Long complaintId;

}