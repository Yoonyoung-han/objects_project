package com.objects.marketbridge.common.infra.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    // TODO
    private Long prevId;
    // TODO
    private Long nextId;

    private String name;

    @Builder
    private CategoryEntity(Long prevId, Long nextId, String name) {
        this.prevId = prevId;
        this.nextId = nextId;
        this.name = name;
    }
}
