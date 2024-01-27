package com.objects.marketbridge.common.infra.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    //String으로 할것. 예시 : ImageType.ITEM_IMG.toString()
    private String type;

    private String url;

    @Builder
    private ImageEntity(String type, String url) {
        this.type = type;
        this.url = url;
    }
}
