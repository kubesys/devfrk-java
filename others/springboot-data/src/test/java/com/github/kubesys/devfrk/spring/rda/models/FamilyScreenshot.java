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
@Table(name = "family_screenshot")
public class FamilyScreenshot {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 32)
    @NotNull
    @Column(name = "student_id", nullable = false, length = 32)
    private String studentId;

    @Size(max = 256)
    @NotNull
    @Column(name = "url", nullable = false, length = 256)
    private String url;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

}