package com.objects.marketbridge.common.infra.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EstimatedTimeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estimated_time_id")
    private Long id;

    private Long hours;

    private Long addDay;

    @Builder
    private EstimatedTimeEntity(Long hour, Long addDay) {
        this.hours = hours;
        this.addDay = addDay;
    }
}
