package com.objects.marketbridge.common.infra.entity;

import com.objects.marketbridge.common.domain.enums.ContentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    private String content;

    @Builder
    private BoardEntity(ContentType contentType, String content) {
        this.contentType = contentType;
        this.content = content;
    }
}
