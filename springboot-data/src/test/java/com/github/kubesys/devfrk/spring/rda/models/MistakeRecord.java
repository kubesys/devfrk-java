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
@Table(name = "mistake_record")
public class MistakeRecord {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "grade")
    private Integer grade;

    @Size(max = 100)
    @NotNull
    @Column(name = "class_num", nullable = false, length = 100)
    private String classNum;

    @NotNull
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Size(max = 64)
    @Column(name = "student_name", length = 64)
    private String studentName;

    @Size(max = 128)
    @NotNull
    @Column(name = "student_username", nullable = false, length = 128)
    private String studentUsername;

    @Size(max = 32)
    @NotNull
    @Column(name = "student_number", nullable = false, length = 32)
    private String studentNumber;

    @Size(max = 100)
    @Column(name = "book_name", length = 100)
    private String bookName;

    @Column(name = "school_org_id")
    private Long schoolOrgId;

    @Size(max = 50)
    @Column(name = "school_org_name", length = 50)
    private String schoolOrgName;

    @Size(max = 50)
    @Column(name = "subject", length = 50)
    private String subject;

    @Size(max = 255)
    @NotNull
    @Column(name = "photo_id", nullable = false)
    private String photoId;

    @Size(max = 50)
    @NotNull
    @Column(name = "url", nullable = false, length = 50)
    private String url;

    @Size(max = 50)
    @NotNull
    @Column(name = "m_id", nullable = false, length = 50)
    private String mId;

    @Size(max = 255)
    @Column(name = "recon_answer")
    private String reconAnswer;

    @Size(max = 50)
    @NotNull
    @Column(name = "create_name", nullable = false, length = 50)
    private String createName;

    @Size(max = 50)
    @NotNull
    @Column(name = "update_name", nullable = false, length = 50)
    private String updateName;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private OffsetDateTime updateTime;
    @Column(name = "question_id")
    private Long questionId;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = false;
    @Column(name = "single_record_id")
    private Long singleRecordId;

    @NotNull
    @Column(name = "create_type", nullable = false)
    private Short createType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autonomic_learn_id")
    private InitiativeLearnInitiativelearntaskhomework autonomicLearn;

/*
    TODO [JPA Buddy] create field to map the 'grades' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "grades", columnDefinition = "int4[]")
    private Object grades;
*/
/*
    TODO [JPA Buddy] create field to map the 'clazz_org_ids' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "clazz_org_ids", columnDefinition = "int8[]")
    private Object clazzOrgIds;
*/
/*
    TODO [JPA Buddy] create field to map the 'clazz_org_names' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "clazz_org_names", columnDefinition = "varchar[]")
    private Object clazzOrgNames;
*/
}