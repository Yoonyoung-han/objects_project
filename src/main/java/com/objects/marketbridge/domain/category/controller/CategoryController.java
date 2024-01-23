package com.objects.marketbridge.domain.category.controller;

import com.objects.marketbridge.domain.category.repository.CategoryRepository;
import com.objects.marketbridge.domain.model.Category;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("categories/uploadExcel")
    public String uploadExcelFile(@RequestParam("file") MultipartFile file) {

        // 엑셀 파일 읽기 : Workbook workbook = new XSSFWorkbook(file.getInputStream())
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())){

            // 첫 번째 시트 선택
            Sheet sheet = workbook.getSheetAt(0);

            // 각 행 처리
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // 첫 번째 행은 건너뛰기
                if (row.getRowNum() == 0) {
                    continue;
                }

                // 필요한 열의 데이터 추출
                String largeCategoryName = row.getCell(0).getStringCellValue(); // 대분류 카테고리 이름
                String mediumCategoryName = row.getCell(1).getStringCellValue(); // 중분류 카테고리 이름
                String smallCategoryName = row.getCell(2).getStringCellValue(); // 소분류 카테고리 이름


                // 중복 체크
                Category largeCategory;
                if (categoryRepository.existsByName(largeCategoryName)) {
                    // 대분류가 이미 존재하는 경우 처리
                    largeCategory = categoryRepository.findByName(largeCategoryName);
                } else {
                    // 대분류가 존재하지 않는 경우 처리
                    largeCategory = new Category(null, 0L, largeCategoryName); // 대분류는 level 0
                    categoryRepository.save(largeCategory);
                }

                // 중복 체크 및 중분류 등록
                Category mediumCategory;
                if (categoryRepository.existsByName(mediumCategoryName)) {
                    // 중분류가 이미 존재하는 경우 처리
                    mediumCategory = categoryRepository.findByName(mediumCategoryName);
                } else {
                    mediumCategory = new Category(largeCategory.getId(), 1L, mediumCategoryName); // 중분류는 level 1
                    categoryRepository.save(mediumCategory);
                }

                // 중복 체크 및 소분류 등록
                if (!categoryRepository.existsByName(smallCategoryName)) {
                    Category smallCategory = new Category(mediumCategory.getId(), 2L, smallCategoryName); // 소분류는 level 2
                    categoryRepository.save(smallCategory);
                }

            }

            //try-with-resource로 변경해서 필요없음.
//            // 리소스 정리
//            workbook.close();

            return "파일 업로드 및 데이터베이스 저장이 완료되었습니다.";

        } catch (IOException e) {
            e.printStackTrace();
            return "파일 업로드 및 처리 중 오류가 발생했습니다.";
        }
    }
}