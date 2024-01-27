package com.objects.marketbridge.common.infra.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewSurveyCategoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_survey_category_id")
    private Long id;

    // TODO
    private Long productId;

    private String name;

    @Builder
    private ReviewSurveyCategoryEntity(Long productId, String name) {
        this.productId = productId;
        this.name = name;
    }
}
