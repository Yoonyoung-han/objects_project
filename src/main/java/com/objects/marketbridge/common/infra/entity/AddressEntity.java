package com.objects.marketbridge.common.infra.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Embedded
    private AddressValue addressValue;

    private Boolean isDefault;


    @Builder
    private AddressEntity(MemberEntity member, AddressValue addressValue, boolean isDefault) {
        this.member = member;
        this.addressValue = addressValue;
        this.isDefault = isDefault;
    }

    // 연관관계 편의 메서드 -> Address 쪽에서 한번에 저장
    public void setMember(MemberEntity member) {
        if (this.member != null) {
            this.member.getAddressEntities().remove(this);
        }
        this.member = member;
        member.getAddressEntities().add(this);
    }
}
