package com.objects.marketbridge.common.infra.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewSurveyEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_survey_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewEntity reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_survey_category_id")
    private ReviewSurveyCategoryEntity reviewSurveyCategoryId;

    // 추가
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_content_id")
    private SurveyContentEntity surveyContentId;

    @Builder
    private ReviewSurveyEntity(ReviewEntity reviewId, ReviewSurveyCategoryEntity reviewSurveyCategoryId , SurveyContentEntity surveyContentId) {
        this.reviewId = reviewId;
        this.reviewSurveyCategoryId = reviewSurveyCategoryId;
        this.surveyContentId = surveyContentId;
    }
}
