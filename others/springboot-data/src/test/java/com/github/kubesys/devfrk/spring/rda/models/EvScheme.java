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
@Table(name = "ev_scheme")
public class EvScheme {
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

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull
    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "score_initial")
    private Integer scoreInitial;

    @Column(name = "score_total")
    private Integer scoreTotal;

    @Column(name = "public_start_time")
    private OffsetDateTime publicStartTime;

    @Column(name = "public_end_time")
    private OffsetDateTime publicEndTime;

    @Column(name = "phrase_study")
    private Integer phraseStudy;

    @NotNull
    @Column(name = "use_status", nullable = false)
    private Integer useStatus;

    @Column(name = "indicator_count")
    private Integer indicatorCount;

    @Column(name = "score_related_student")
    private Boolean scoreRelatedStudent;

    @Size(max = 32)
    @Column(name = "school_org_id", length = 32)
    private String schoolOrgId;

    @Size(max = 50)
    @Column(name = "school_org_name", length = 50)
    private String schoolOrgName;

    @Column(name = "clazz_org_id")
    private Long clazzOrgId;

    @Size(max = 50)
    @Column(name = "clazz_org_name", length = 50)
    private String clazzOrgName;

    @Column(name = "publisher_id")
    private Long publisherId;

    @Size(max = 50)
    @Column(name = "publisher_name", length = 50)
    private String publisherName;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "semester_id")
    private Long semesterId;
    @Size(max = 500)
    @Column(name = "temp_data", length = 500)
    private String tempData;

/*
    TODO [JPA Buddy] create field to map the 'enrollment_years' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "enrollment_years", columnDefinition = "int4[] not null")
    private Object enrollmentYears;
*/
}