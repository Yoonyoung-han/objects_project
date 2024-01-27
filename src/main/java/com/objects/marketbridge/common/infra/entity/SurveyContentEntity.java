package com.objects.marketbridge.common.infra.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyContentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_content_id")
    private Long id;

    // TODO
    private Long surveyCategoryId;

    private Integer seqNo; // 1,2,3

    private String content;

    @Builder
    private SurveyContentEntity(Long surveyCategoryId, Integer seqNo, String content) {
        this.surveyCategoryId = surveyCategoryId;
        this.seqNo = seqNo;
        this.content = content;
    }
}
