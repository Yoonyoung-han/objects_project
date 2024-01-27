package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

    //JpaRepository에서 제공되는 기본메서드 사용
    Optional<MemberEntity> findByEmail(String email);

    Optional<MemberEntity> findMemberById(Long id);

    @Query("SELECT DISTINCT m FROM MemberEntity m JOIN FETCH m.addresses WHERE m.id = :memberId")
    Optional<MemberEntity> findByIdWithAddresses(@Param("memberId") Long id);

    @Query("SELECT DISTINCT m FROM MemberEntity m JOIN FETCH m.point WHERE m.id = :memberId")
    Optional<MemberEntity> findByIdWithPoint(@Param("memberId") Long id);

}
