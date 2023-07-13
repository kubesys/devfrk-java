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
@Table(name = "homework_studentpapetestrecord")
public class HomeworkStudentpapetestrecord {
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
    @Column(name = "student_id", nullable = false, length = 50)
    private String studentId;

    @Size(max = 50)
    @NotNull
    @Column(name = "student_name", nullable = false, length = 50)
    private String studentName;

    @Size(max = 50)
    @NotNull
    @Column(name = "student_number", nullable = false, length = 50)
    private String studentNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "clazz_id", nullable = false, length = 50)
    private String clazzId;

    @Size(max = 50)
    @NotNull
    @Column(name = "clazz_name", nullable = false, length = 50)
    private String clazzName;

    @Size(max = 50)
    @NotNull
    @Column(name = "subject", nullable = false, length = 50)
    private String subject;

    @Column(name = "score")
    private Double score;

    @NotNull
    @Column(name = "paper_test_record_id", nullable = false)
    private Long paperTestRecordId;

    @NotNull
    @Column(name = "student_homework_id", nullable = false)
    private Long studentHomeworkId;

}