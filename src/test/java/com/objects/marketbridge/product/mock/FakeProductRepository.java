package com.objects.marketbridge.product.mock;

import com.objects.marketbridge.domains.product.domain.Product;
import com.objects.marketbridge.domains.product.service.port.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class FakeProductRepository implements ProductRepository {

    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    List<Product> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public Product findByProductNo(String productNo) {
        return null;
    }

    @Override
    public List<Product> findAllById(List<Long> ids) {
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        return null;
    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null || product.getId() == 0){
            Product newProduct = Product.builder()
                    .id(autoGeneratedId.incrementAndGet())
                    .productNo(product.getProductNo())
                    .price(product.getPrice())
                    .name(product.getName())
                    .thumbImg(product.getThumbImg())
                    .discountRate(product.getDiscountRate())
                    .isOwn(product.getIsOwn())
                    .stock(product.getStock())
                    .build();
            data.add(newProduct);
            return newProduct;
        } else {
            data.removeIf(item -> Objects.equals(item.getId(), product.getId()));
            data.add(product);
            return product;
        }
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void saveAll(List<Product> products) {
        for (Product product: products) {
            if (product.getId() == null || product.getId() == 0){
                data.add(Product.builder()
                        .id(autoGeneratedId.incrementAndGet())
                        .productNo(product.getProductNo())
                        .price(product.getPrice())
                        .name(product.getName())
                        .thumbImg(product.getThumbImg())
                        .discountRate(product.getDiscountRate())
                        .isOwn(product.getIsOwn())
                        .stock(product.getStock())
                        .build());
            } else {
                data.removeIf(item -> Objects.equals(item.getId(), product.getId()));
                data.add(product);
            }
        }
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public Page<Product> findAllByCategoryId(Pageable pageable, Long categoryId) {
        return null;
    }
}
