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
@Table(name = "rs_kp2student")
public class RsKp2student {
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

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "school_org_id", nullable = false)
    private Long schoolOrgId;

    @Size(max = 64)
    @NotNull
    @Column(name = "school_org_name", nullable = false, length = 64)
    private String schoolOrgName;

    @NotNull
    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Size(max = 64)
    @Column(name = "teacher_nickname", length = 64)
    private String teacherNickname;

    @Size(max = 128)
    @NotNull
    @Column(name = "teacher_username", nullable = false, length = 128)
    private String teacherUsername;

    @NotNull
    @Column(name = "clazz_org_id", nullable = false)
    private Long clazzOrgId;

    @Size(max = 64)
    @Column(name = "clazz_org_name", length = 64)
    private String clazzOrgName;

    @Column(name = "student_id")
    private Long studentId;

    @Size(max = 128)
    @Column(name = "student_username", length = 128)
    private String studentUsername;

    @Size(max = 64)
    @Column(name = "student_nickname", length = 64)
    private String studentNickname;

    @Size(max = 32)
    @Column(name = "student_number", length = 32)
    private String studentNumber;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "catalog_id")
    private Long catalogId;

    @Column(name = "edition_id")
    private Long editionId;

    @Column(name = "knowledge_point_resource_id")
    private Long knowledgePointResourceId;

    @Column(name = "subject_id")
    private Long subjectId;

}