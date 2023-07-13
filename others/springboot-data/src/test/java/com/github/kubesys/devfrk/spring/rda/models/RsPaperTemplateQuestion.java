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
@Table(name = "rs_paper_template_questions")
public class RsPaperTemplateQuestion {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "papertemplate_id", nullable = false)
    private Long papertemplateId;

    @NotNull
    @Column(name = "question_id", nullable = false)
    private Long questionId;

}