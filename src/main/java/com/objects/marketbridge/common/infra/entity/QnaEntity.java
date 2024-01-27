package com.objects.marketbridge.common.infra.entity;

import com.objects.marketbridge.common.domain.enums.ContentType;
import com.objects.marketbridge.product.seller.domain.Seller;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberId; //memberId

    // @AttributeOverride()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller sellerId; //memberId

    @Enumerated(EnumType.STRING)
    private ContentType boardType;

    private String content;

    @Builder
    private QnaEntity(MemberEntity memberId, Seller sellerId, ContentType boardType, String content) {
        this.memberId = memberId;
        this.sellerId = sellerId;
        this.boardType = boardType;
        this.content = content;
    }
}
