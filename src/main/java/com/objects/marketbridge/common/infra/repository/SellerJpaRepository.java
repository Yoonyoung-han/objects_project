package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.product.seller.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerJpaRepository extends JpaRepository<Seller, Long> {
}
