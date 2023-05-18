package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.*;
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
@Table(name = "resources_folder")
public class ResourcesFolder {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 128)
    @NotNull
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Size(max = 32)
    @NotNull
    @Column(name = "owner", nullable = false, length = 32)
    private String owner;

    @Size(max = 32)
    @NotNull
    @Column(name = "type", nullable = false, length = 32)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private RsFileResource file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private ResourcesFolder folder;

    @Size(max = 32)
    @NotNull
    @Column(name = "category", nullable = false, length = 32)
    private String category;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Size(max = 32)
    @NotNull
    @Column(name = "subject", nullable = false, length = 32)
    private String subject;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @NotNull
    @Column(name = "schools", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> schools;

}