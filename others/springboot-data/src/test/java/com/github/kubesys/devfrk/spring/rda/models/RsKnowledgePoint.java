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
@Table(name = "rs_knowledge_point")
public class RsKnowledgePoint {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "create_name", length = 50)
    private String createName;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    @Size(max = 50)
    @Column(name = "update_name", length = 50)
    private String updateName;

    @Column(name = "update_time")
    private OffsetDateTime updateTime;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = false;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "school_org_name")
    private String schoolOrgName;

    @Size(max = 255)
    @Column(name = "school_org_id")
    private String schoolOrgId;

    @Size(max = 50)
    @Column(name = "upload_user", length = 50)
    private String uploadUser;

    @Column(name = "upload_userid")
    private Long uploadUserid;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status = false;

    @Column(name = "am_book_id")
    private Long amBookId;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "catalog_id")
    private Long catalogId;

    @Column(name = "edition_id")
    private Long editionId;

    @Column(name = "subject_id")
    private Long subjectId;

}