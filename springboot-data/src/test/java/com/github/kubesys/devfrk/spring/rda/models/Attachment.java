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
@Table(name = "attachment")
public class Attachment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 256)
    @NotNull
    @Column(name = "file_name", nullable = false, length = 256)
    private String fileName;

    @Size(max = 255)
    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

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