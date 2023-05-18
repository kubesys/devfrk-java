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
@Table(name = "control")
public class Control {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @NotNull
    @Column(name = "school_org_id", nullable = false)
    private Long schoolOrgId;

    @NotNull
    @Column(name = "clazz_org_id", nullable = false)
    private Long clazzOrgId;

    @Size(max = 255)
    @NotNull
    @Column(name = "clazz_name", nullable = false)
    private String clazzName;

    @Size(max = 50)
    @Column(name = "subject", length = 50)
    private String subject;

    @NotNull
    @Column(name = "enable", nullable = false)
    private Boolean enable = false;

    @NotNull
    @Column(name = "expired_at", nullable = false)
    private OffsetDateTime expiredAt;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

}