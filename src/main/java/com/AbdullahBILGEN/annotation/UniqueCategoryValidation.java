package com.AbdullahBILGEN.annotation;

import com.AbdullahBILGEN.data.repository.IToDoRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

// LOMBOK
@RequiredArgsConstructor

// Annotation
public class UniqueCategoryValidation implements ConstraintValidator<UniqueCategoryName,String> {

    // Injection
    private final IToDoRepository iToDoRepository;

    // Database'de bu kategori isminden var mı ?
    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext constraintValidatorContext) {
        Boolean isOtherCategoryName= iToDoRepository.findByTodoContent(categoryName).isPresent();
        //Eğer database böyle bir category adı varsa bilgilendirme yapsın
        if(isOtherCategoryName){
            return false;
        }
        return true;
    }
}
