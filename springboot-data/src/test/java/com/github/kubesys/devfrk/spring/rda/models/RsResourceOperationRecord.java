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
@Table(name = "rs_resource_operation_record")
public class RsResourceOperationRecord {
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

    @Size(max = 32)
    @NotNull
    @Column(name = "resource_type", nullable = false, length = 32)
    private String resourceType;

    @NotNull
    @Column(name = "resource_id", nullable = false)
    private Long resourceId;

    @Size(max = 32)
    @NotNull
    @Column(name = "operation_type", nullable = false, length = 32)
    private String operationType;

    @NotNull
    @Column(name = "operation_user_id", nullable = false)
    private Long operationUserId;

    @Size(max = 64)
    @Column(name = "operation_user_name", length = 64)
    private String operationUserName;

    @Size(max = 128)
    @Column(name = "operation_user_username", length = 128)
    private String operationUserUsername;

    @NotNull
    @Column(name = "withdraw_status", nullable = false)
    private Boolean withdrawStatus = false;
    @Size(max = 255)
    @NotNull
    @Column(name = "resource_name", nullable = false)
    private String resourceName;

/*
    TODO [JPA Buddy] create field to map the 'clazz_org_ids' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "clazz_org_ids", columnDefinition = "varchar[]")
    private Object clazzOrgIds;
*/
}