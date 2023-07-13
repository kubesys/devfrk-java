package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "resources_publish")
public class ResourcesPublish {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 128)
    @NotNull
    @Column(name = "subject", nullable = false, length = 128)
    private String subject;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Size(max = 32)
    @NotNull
    @Column(name = "publisher", nullable = false, length = 32)
    private String publisher;
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "folder_id", nullable = false)
    private ResourcesFolder folder;

/*
    TODO [JPA Buddy] create field to map the 'student_class' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "student_class", columnDefinition = "varchar[] not null")
    private Object studentClass;
*/
}