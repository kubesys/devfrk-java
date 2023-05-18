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
@Table(name = "family_parentswechatuser")
public class FamilyParentswechatuser {
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

    @Size(max = 20)
    @Column(name = "user_id", length = 20)
    private String userId;

    @Size(max = 11)
    @Column(name = "parent_mobile", length = 11)
    private String parentMobile;

    @Size(max = 128)
    @Column(name = "openid", length = 128)
    private String openid;

    @Size(max = 128)
    @Column(name = "session_key", length = 128)
    private String sessionKey;

}