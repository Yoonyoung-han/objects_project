package com.objects.marketbridge.product.seller.repository;

import com.objects.marketbridge.product.seller.domain.Seller;
import com.objects.marketbridge.product.seller.domain.SellerAccount;

import java.util.List;

public interface SellerRepository {

    Seller findSellerById(Long id);
    List<SellerAccount> findAllSellerAccount();
    List<Seller> findAllSeller();

    SellerAccount findSellerAccountBySellerId(Long sellerId);

    SellerAccount save(SellerAccount sellerAccount);
    Seller save(Seller seller);
}
