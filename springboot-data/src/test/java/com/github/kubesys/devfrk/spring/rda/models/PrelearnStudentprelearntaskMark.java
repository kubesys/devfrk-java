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
@Table(name = "prelearn_studentprelearntask_marks")
public class PrelearnStudentprelearntaskMark {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "studentprelearntask_id", nullable = false)
    private Long studentprelearntaskId;

    @NotNull
    @Column(name = "fileresource_id", nullable = false)
    private Long fileresourceId;

}