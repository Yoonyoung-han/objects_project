package com.objects.marketbridge.domain.product.repository;

import com.objects.marketbridge.common.infra.entity.ProductEntity;
import com.objects.marketbridge.product.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProductRepositoryImplTest {

    @Autowired
    ProductRepository productRepository;

    @AfterEach
    void tearDown(){
        productRepository.deleteAllInBatch();
    }

    @DisplayName("상품명으로 물건찾기")
    @Test
    void findByName(){
        //given
        String productName = "가방";
        productRepository.save(ProductEntity.builder().name(productName).build());

        //when
        List<ProductEntity> products = productRepository.findByName(productName);

        //then
        assertThat(products).isInstanceOf(List.class);
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo(productName);

    }


}