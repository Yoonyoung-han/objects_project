package com.objects.marketbridge.member.repository;

import com.objects.marketbridge.common.infra.entity.Member;

public interface MemberCustomRepository {
    //JPA가 지원하지 않는 기능

    Member findByIdWithPointAndAddresses(Long id);

}
