package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "question_analysisofjy")
public class QuestionAnalysisofjy {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 500)
    @NotNull
    @Column(name = "jy_sid", nullable = false, length = 500)
    private String jySid;

    @Column(name = "analysis", length = Integer.MAX_VALUE)
    private String analysis;

    @NotNull
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @NotNull
    @Column(name = "jy_relation_id", nullable = false)
    private Long jyRelationId;

}