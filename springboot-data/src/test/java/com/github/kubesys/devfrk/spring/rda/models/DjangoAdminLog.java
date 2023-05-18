package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "django_admin_log")
public class DjangoAdminLog {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "action_time", nullable = false)
    private OffsetDateTime actionTime;

    @Column(name = "object_id", length = Integer.MAX_VALUE)
    private String objectId;

    @Size(max = 200)
    @NotNull
    @Column(name = "object_repr", nullable = false, length = 200)
    private String objectRepr;

    @NotNull
    @Column(name = "action_flag", nullable = false)
    private Short actionFlag;

    @NotNull
    @Column(name = "change_message", nullable = false, length = Integer.MAX_VALUE)
    private String changeMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_type_id")
    private DjangoContentType contentType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser user;

}