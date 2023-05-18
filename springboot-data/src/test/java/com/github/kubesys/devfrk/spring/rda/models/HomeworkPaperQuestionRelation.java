package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "homework_paper_question_relation")
public class HomeworkPaperQuestionRelation {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @NotNull
    @Column(name = "mistake_created", nullable = false)
    private Boolean mistakeCreated = false;

    @NotNull
    @Column(name = "paper_id", nullable = false)
    private Long paperId;

    @NotNull
    @Column(name = "question_id", nullable = false)
    private Long questionId;

}