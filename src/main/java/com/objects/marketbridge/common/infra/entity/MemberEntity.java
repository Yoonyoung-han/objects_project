package com.objects.marketbridge.common.infra.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class MemberEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String socialType;
    @Setter
    private String membership;

    private String email;

    private String password;

    private String name;

    private String phoneNo;

    // 알림
    private Boolean isAlert;
    // 약관동의
    private Boolean isAgree;


    // 양방향 설정
    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressEntity> addressEntities = new ArrayList<>();

    @OneToOne(mappedBy = "member" , cascade = CascadeType.ALL, orphanRemoval = true)
    private PointEntity point;

    @Builder
    public MemberEntity(Long id, String socialType, String membership, String email, String password, String name, String phoneNo, Boolean isAlert, Boolean isAgree) {
        this.id = id;
        this.socialType = socialType;
        this.membership = membership;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNo = phoneNo;
        this.isAlert = isAlert;
        this.isAgree = isAgree;
    }

    public void changePoint(PointEntity point) {
        this.point = point;
    }

//    public static Member fromDto(CreateMember createMember){
//        return Member.builder()
//                .email(createMember.getEmail())
//                .name(createMember.getName())
//                .phoneNo(createMember.getPhoneNo())
//                .password(createMember.getPassword())
//                .isAgree(createMember.getIsAgree()).build();
//    }
}


