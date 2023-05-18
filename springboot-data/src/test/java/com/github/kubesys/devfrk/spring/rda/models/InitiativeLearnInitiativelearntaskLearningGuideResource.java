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
@Table(name = "initiative_learn_initiativelearntask_learning_guide_resource")
public class InitiativeLearnInitiativelearntaskLearningGuideResource {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "initiativelearntask_id", nullable = false)
    private Long initiativelearntaskId;

    @NotNull
    @Column(name = "learningguideresource_id", nullable = false)
    private Long learningguideresourceId;

}