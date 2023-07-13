package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "homework_papertestrecord")
public class HomeworkPapertestrecord {
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

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 50)
    @NotNull
    @Column(name = "subject", nullable = false, length = 50)
    private String subject;

    @Column(name = "exam_time")
    private LocalDate examTime;

    @Column(name = "grade")
    private Integer grade;

    @NotNull
    @Column(name = "enrollment_year", nullable = false)
    private Integer enrollmentYear;

    @Column(name = "phrase_year")
    private Integer phraseYear;

    @Column(name = "phrase_study")
    private Integer phraseStudy;

    @Column(name = "status")
    private Integer status;

    @Column(name = "homework_id")
    private Long homeworkId;

}