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
@Table(name = "question_of_jy")
public class QuestionOfJy {
    @Id
    @Size(max = 255)
    @Column(name = "sid", nullable = false)
    private String id;

    @NotNull
    @Column(name = "raw_question", nullable = false, length = Integer.MAX_VALUE)
    private String rawQuestion;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

}