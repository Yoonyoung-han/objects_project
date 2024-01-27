package com.objects.marketbridge.common.infra.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 소셜정보
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialCredentialEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_credential_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberId;

    private String tokenId;

    @Builder
    private SocialCredentialEntity(MemberEntity memberId, String tokenId) {
        this.memberId = memberId;
        this.tokenId = tokenId;
    }
}
