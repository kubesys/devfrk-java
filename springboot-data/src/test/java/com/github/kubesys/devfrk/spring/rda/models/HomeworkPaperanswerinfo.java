package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "homework_paperanswerinfo")
public class HomeworkPaperanswerinfo {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 64)
    @NotNull
    @Column(name = "student_id", nullable = false, length = 64)
    private String studentId;

    @NotNull
    @Column(name = "info", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> info;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paper_id", nullable = false)
    private HomeworkPaper paper;

}