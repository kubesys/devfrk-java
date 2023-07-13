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
@Table(name = "device")
public class Device {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 15)
    @NotNull
    @Column(name = "serial_number", nullable = false, length = 15)
    private String serialNumber;

    @Column(name = "student_id")
    private Long studentId;

    @Size(max = 15)
    @Column(name = "version_code", length = 15)
    private String versionCode;

    @Size(max = 255)
    @Column(name = "stu_name")
    private String stuName;

    @Size(max = 255)
    @Column(name = "stu_num")
    private String stuNum;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Size(max = 255)
    @Column(name = "tch_name")
    private String tchName;

    @Size(max = 50)
    @Column(name = "create_name", length = 50)
    private String createName;

    @Size(max = 50)
    @Column(name = "update_name", length = 50)
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

    @Size(max = 255)
    @Column(name = "username")
    private String username;

    @Size(max = 255)
    @Column(name = "pwd")
    private String pwd;

    @NotNull
    @Column(name = "is_student", nullable = false)
    private Boolean isStudent = false;

}