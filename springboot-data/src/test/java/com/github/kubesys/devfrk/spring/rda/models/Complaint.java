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
@Table(name = "complaint")
public class Complaint {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 16)
    @NotNull
    @Column(name = "province", nullable = false, length = 16)
    private String province;

    @Size(max = 16)
    @Column(name = "city", length = 16)
    private String city;

    @Size(max = 16)
    @Column(name = "area", length = 16)
    private String area;

    @Size(max = 64)
    @NotNull
    @Column(name = "target", nullable = false, length = 64)
    private String target;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Size(max = 11)
    @NotNull
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Size(max = 1024)
    @NotNull
    @Column(name = "content", nullable = false, length = 1024)
    private String content;

    @Size(max = 1024)
    @Column(name = "note", length = 1024)
    private String note;

    @NotNull
    @Column(name = "state", nullable = false)
    private Integer state;

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

}