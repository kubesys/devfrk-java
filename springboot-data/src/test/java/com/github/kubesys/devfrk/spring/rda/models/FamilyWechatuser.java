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
@Table(name = "family_wechatuser")
public class FamilyWechatuser {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 11)
    @NotNull
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Size(max = 128)
    @NotNull
    @Column(name = "openid", nullable = false, length = 128)
    private String openid;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

}