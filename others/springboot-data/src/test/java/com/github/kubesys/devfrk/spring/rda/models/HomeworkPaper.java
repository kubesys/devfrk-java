package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "homework_paper")
public class HomeworkPaper {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Size(max = 50)
    @Column(name = "subject", length = 50)
    private String subject;

    @Column(name = "question_count")
    private Integer questionCount;

    @NotNull
    @Column(name = "publish_count", nullable = false)
    private Integer publishCount;

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
    @Column(name = "teacher_name", length = 64)
    private String teacherName;

    @Size(max = 128)
    @NotNull
    @Column(name = "teacher_username", nullable = false, length = 128)
    private String teacherUsername;

    @Column(name = "student_id")
    private Long studentId;

    @Size(max = 64)
    @Column(name = "student_name", length = 64)
    private String studentName;

    @Size(max = 128)
    @Column(name = "student_username", length = 128)
    private String studentUsername;

    @Size(max = 32)
    @Column(name = "student_number", length = 32)
    private String studentNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "create_name", nullable = false, length = 50)
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

    @NotNull
    @Column(name = "is_objective", nullable = false)
    private Boolean isObjective = false;

    @Column(name = "points", length = Integer.MAX_VALUE)
    private String points;

    @Size(max = 50)
    @Column(name = "type", length = 50)
    private String type;

    @NotNull
    @Column(name = "is_set_score", nullable = false)
    private Boolean isSetScore = false;

    @NotNull
    @Column(name = "schools", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> schools;

}