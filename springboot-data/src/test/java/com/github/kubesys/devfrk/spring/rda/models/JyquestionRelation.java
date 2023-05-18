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
@Table(name = "jyquestion_relation")
public class JyquestionRelation {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Size(max = 255)
    @NotNull
    @Column(name = "question_of_jy_id", nullable = false)
    private String questionOfJyId;

}