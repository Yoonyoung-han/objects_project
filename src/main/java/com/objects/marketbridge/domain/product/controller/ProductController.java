package com.objects.marketbridge.domain.product.controller;

import com.objects.marketbridge.domain.product.dto.*;
import com.objects.marketbridge.domain.product.service.ProductService;
import com.objects.marketbridge.global.common.ApiResponse;
import com.objects.marketbridge.global.security.mock.UserAuthorize;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    //상품등록
    @UserAuthorize
    @PostMapping("/products")
    public ApiResponse<CreateProductResponseDto> createProduct
    (@Valid @RequestBody CreateProductRequestDto createProductRequestDto) {
        Long productId = productService.createProduct(createProductRequestDto);
        CreateProductResponseDto createProductResponseDto = new CreateProductResponseDto(productId);
        return ApiResponse.ok(createProductResponseDto);
    }



    //상품조회
    @UserAuthorize
    @GetMapping("/products/{id}")
    public ApiResponse<ReadProductResponseDto> readProduct
    (@PathVariable("id") Long id){
        ReadProductResponseDto readProductResponseDto = productService.readProduct(id);
        return ApiResponse.ok(readProductResponseDto);
    }



//    //상품수정
//    @UserAuthorize
//    @PatchMapping("/products/{id}")
//    public ApiResponse<UpdateProductResponseDto> updateProduct
//    (@PathVariable("id") Long id, @RequestBody @Valid UpdateProductRequestDto updateProductRequestDto) {
//        productService.updateProduct(id, updateProductRequestDto);
//        UpdateProductResponseDto updateProductResponseDto = new UpdateProductResponseDto(id);
//        return ApiResponse.ok(updateProductResponseDto);
//    }



//    //상품삭제
//    @UserAuthorize
//    @DeleteMapping("/products/{id}")
//    public ApiResponse<ProductResponseDto> deleteProduct
//    (@PathVariable("id") Long id) {
//        productService.deleteProduct(id);
//        ProductResponseDto productResponseDto = new ProductResponseDto();
//        return ApiResponse.ok(productResponseDto);
//    }



}
