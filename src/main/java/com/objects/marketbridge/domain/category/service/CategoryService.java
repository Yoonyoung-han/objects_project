package com.objects.marketbridge.domain.category.service;

import com.objects.marketbridge.domain.category.dto.ReadCategoryResponseDto;
import com.objects.marketbridge.domain.category.repository.CategoryRepository;
import com.objects.marketbridge.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Transactional
    public String uploadExcelFile(MultipartFile file) {

        // 엑셀 파일 읽기 : Workbook workbook = new XSSFWorkbook(file.getInputStream())
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())){

            // 첫 번째 시트 선택
            Sheet sheet = workbook.getSheetAt(0);

            //==========대분류==========
            // 각 행 처리
            Iterator<Row> rowIterator0 = sheet.iterator();
            while (rowIterator0.hasNext()) {
                Row row = rowIterator0.next();
                // 첫 번째 행은 건너뛰기
                if (row.getRowNum() == 0) {
                    continue;
                }
                // 필요한 열의 데이터 추출
                String largeCategoryName = row.getCell(0).getStringCellValue(); // 대분류 카테고리 이름
                //대분류 등록
                Category largeCategory;
                if (categoryRepository.existsByName(largeCategoryName)) {
                    // 대분류가 이미 존재하는 경우 처리
                    largeCategory = categoryRepository.findByName(largeCategoryName);
                } else {
                    // 대분류가 존재하지 않는 경우 처리
                    largeCategory = new Category(null, 0L, largeCategoryName);
                    categoryRepository.save(largeCategory);
                }
            }

            // ==========중분류==========
            // 각 행 처리
            Iterator<Row> rowIterator1 = sheet.iterator();
            while (rowIterator1.hasNext()) {
                Row row = rowIterator1.next();

                // 첫 번째 행은 건너뛰기
                if (row.getRowNum() == 0) {
                    continue;
                }

                // 필요한 열의 데이터 추출
                String largeCategoryName = row.getCell(0).getStringCellValue(); // 대분류 카테고리 이름
                String mediumCategoryName = row.getCell(1).getStringCellValue(); // 중분류 카테고리 이름

                Category largeCategory = categoryRepository.findByNameAndLevel(largeCategoryName, 0L);

                // 중복 체크 및 중분류 등록
                Category mediumCategory;

                // 중분류명이 이미 존재하는 경우 처리
                if (categoryRepository.existsByNameAndLevel(mediumCategoryName,1L)) {
                    //해당 중분류의대분류id와
                    // 이미존재하는대분류id가 다르면 DB에 등록.
                    List<Long> alreadyExistLargeCategoryIds
                            = categoryRepository.findAllByNameAndLevel(mediumCategoryName, 1L)
                            .stream()
                            .map(category -> category.getParentId())
                            .collect(Collectors.toList());
                    int count = 0;
                    for (Long alreadyExistLargeCategoryId : alreadyExistLargeCategoryIds) {
                        if(largeCategory.getId() == alreadyExistLargeCategoryId){
                            count++;
                        }
                    }
                    if(count == 0){
                        mediumCategory = new Category(largeCategory.getId(), 1L, mediumCategoryName);
                        categoryRepository.save(mediumCategory);
                    }
                } else {
                    mediumCategory = new Category(largeCategory.getId(), 1L, mediumCategoryName);
                    categoryRepository.save(mediumCategory);
                }
            }

            //==========소분류==========
            Iterator<Row> rowIterator2 = sheet.iterator();
            while (rowIterator2.hasNext()) {
                Row row = rowIterator2.next();

                // 첫 번째 행은 건너뛰기
                if (row.getRowNum() == 0) {
                    continue;
                }
                //필요한 열의 데이터 추출
                String largeCategoryName = row.getCell(0).getStringCellValue(); // 대분류 카테고리 이름
                String mediumCategoryName = row.getCell(1).getStringCellValue(); // 중분류 카테고리 이름
                String smallCategoryName = row.getCell(2).getStringCellValue(); // 소분류 카테고리 이름
                // 중복 체크 및 소분류 등록
                Category smallCategory;
                List<Category> mediumCategoriesToBeCompared
                        = categoryRepository.findAllByNameAndLevel(mediumCategoryName, 1L);
                for (Category mediumCategoryToBeCompared : mediumCategoriesToBeCompared) {
                    Category largeCategoryToBeCompared
                            = categoryRepository.findById(mediumCategoryToBeCompared.getParentId());
                    if (largeCategoryToBeCompared.getName().equals(largeCategoryName)) {
                        smallCategory = new Category(mediumCategoryToBeCompared.getId(), 2L, smallCategoryName);
                        categoryRepository.save(smallCategory);
                        break;
                    }
                }
            }

//            //try-with-resource로 변경해서 필요없음.
//            //리소스 정리
//            workbook.close();

            return "파일 업로드 및 데이터베이스 저장이 완료되었습니다.";

        } catch (IOException e) {
            e.printStackTrace();
            return "파일 업로드 및 처리 중 오류가 발생했습니다.";
        }

    } //uploadExcelFile



//    public List<Category> getAllCategories() {
//    }

//    public List<Category> getLargeCategories() {
//        return categoryRepository.findAllByLevelAndParentIdIsNull(0L);
//    }
    public List<ReadCategoryResponseDto> getLargeCategories() {
        List<Category> categories = categoryRepository.findAllByLevelAndParentIdIsNull(0L);
        return convertToDtoList(categories);
    }

//    public List<Category> getMediumCategories(Long parentId) {
//        return categoryRepository.findAllByLevelAndParentId(1L, parentId);
//    }
public List<ReadCategoryResponseDto> getMediumCategories(Long parentId) {
    List<Category> categories = categoryRepository.findAllByLevelAndParentId(1L, parentId);
    return convertToDtoList(categories);
}

//    public List<Category> getSmallCategories(Long parentId) {
//        return categoryRepository.findAllByLevelAndParentId(2L, parentId);
//    }
    public List<ReadCategoryResponseDto> getSmallCategories(Long parentId) {
        List<Category> categories = categoryRepository.findAllByLevelAndParentId(2L, parentId);
        return convertToDtoList(categories);
    }



    //내부 이용 메서드
    private List<ReadCategoryResponseDto> convertToDtoList(List<Category> categories) {
        List<ReadCategoryResponseDto> readCategoryResponseDtos = new ArrayList<>();
        for (Category category : categories) {
            ReadCategoryResponseDto readCategoryResponseDto = new ReadCategoryResponseDto();
            readCategoryResponseDto.setId(category.getId());
            readCategoryResponseDto.setParentId(category.getParentId());
            readCategoryResponseDto.setLevel(category.getLevel());
            readCategoryResponseDto.setName(category.getName());

            // childCategories 필드는 재귀적으로 변환
            readCategoryResponseDto.setChildCategories(convertToDtoList(category.getChildCategories()));

            readCategoryResponseDtos.add(readCategoryResponseDto);
        }
        return readCategoryResponseDtos;
    }




}
