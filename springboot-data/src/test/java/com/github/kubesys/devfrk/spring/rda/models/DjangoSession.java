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
@Table(name = "django_session")
public class DjangoSession {
    @Id
    @Size(max = 40)
    @Column(name = "session_key", nullable = false, length = 40)
    private String id;

    @NotNull
    @Column(name = "session_data", nullable = false, length = Integer.MAX_VALUE)
    private String sessionData;

    @NotNull
    @Column(name = "expire_date", nullable = false)
    private OffsetDateTime expireDate;

}