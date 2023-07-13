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
@Table(name = "app_update")
public class AppUpdate {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "app_name", nullable = false)
    private String appName;

    @Size(max = 127)
    @NotNull
    @Column(name = "version_code", nullable = false, length = 127)
    private String versionCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "download_url", nullable = false)
    private String downloadUrl;

    @Size(max = 511)
    @NotNull
    @Column(name = "update_info", nullable = false, length = 511)
    private String updateInfo;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private OffsetDateTime updateTime;

}