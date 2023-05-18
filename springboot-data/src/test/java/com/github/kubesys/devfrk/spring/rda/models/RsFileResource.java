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
@Table(name = "rs_file_resource")
public class RsFileResource {
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
    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Size(max = 255)
    @NotNull
    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @NotNull
    @Column(name = "file_seq", nullable = false)
    private Integer fileSeq;

    @NotNull
    @Column(name = "file_size", nullable = false)
    private Integer fileSize;

    @Size(max = 50)
    @NotNull
    @Column(name = "file_ext", nullable = false, length = 50)
    private String fileExt;

    @Size(max = 100)
    @NotNull
    @Column(name = "file_key", nullable = false, length = 100)
    private String fileKey;

    @Column(name = "knowledge_point_id")
    private Long knowledgePointId;

    @Column(name = "learning_guide_id")
    private Long learningGuideId;

    @Column(name = "video_id")
    private Long videoId;

}