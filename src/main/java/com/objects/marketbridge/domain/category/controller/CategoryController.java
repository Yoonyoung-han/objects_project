package com.objects.marketbridge.domain.category.controller;

import com.objects.marketbridge.domain.category.dto.ReadCategoryResponseDto;
import com.objects.marketbridge.domain.category.repository.CategoryRepository;
import com.objects.marketbridge.domain.category.service.CategoryService;
import com.objects.marketbridge.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @PostMapping("/categories/uploadExcel")
    public String uploadExcelFile(@RequestParam("file") MultipartFile file){
        return categoryService.uploadExcelFile(file);
    }

//    @GetMapping("/categories/all")
//    public List<Category> getAllCategories() {
//        return categoryService.getAllCategories();
//    }

    @GetMapping("/categories/largeCategories")
    public List<ReadCategoryResponseDto> getLargeCategories() {
        return categoryService.getLargeCategories();
    }

    @GetMapping("/categories/mediumCategories/{parentId}")
    public List<ReadCategoryResponseDto> getMediumCategories(@PathVariable("parentId") Long parentId) {
        return categoryService.getMediumCategories(parentId);
    }

    @GetMapping("/categories/smallCategories/{parentId}")
    public List<ReadCategoryResponseDto> getSmallCategories(@PathVariable("parentId") Long parentId) {
        return categoryService.getSmallCategories(parentId);
    }


}