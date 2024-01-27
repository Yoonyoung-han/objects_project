package com.objects.marketbridge.product.seller.repository;

import com.objects.marketbridge.common.infra.repository.SellerAccountJpaRepository;
import com.objects.marketbridge.common.infra.repository.SellerJpaRepository;
import com.objects.marketbridge.product.seller.domain.Seller;
import com.objects.marketbridge.product.seller.domain.SellerAccount;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SellerRepositoryImpl implements SellerRepository {

    private final SellerJpaRepository sellerJpaRepository;
    private final SellerAccountJpaRepository sellerAccountJpaRepository;

    @Override
    public Seller findSellerById(Long id) {
        return sellerJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<SellerAccount> findAllSellerAccount() {
        return sellerAccountJpaRepository.findAll();
    }

    @Override
    public List<Seller> findAllSeller() {
        return sellerJpaRepository.findAll();
    }

    @Override
    public SellerAccount findSellerAccountBySellerId(Long sellerId) {
        return sellerAccountJpaRepository.findBySellerId(sellerId);
    }

    @Override
    public SellerAccount save(SellerAccount sellerAccount) {
        return sellerAccountJpaRepository.save(sellerAccount);
    }

    @Override
    public Seller save(Seller seller) {
        return sellerJpaRepository.save(seller);
    }
}
