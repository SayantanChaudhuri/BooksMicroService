package com.sayantan.books.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class AuthorNameValidator implements ConstraintValidator<AuthorNameSkipValidator, String> {

    @Value("${skiplist.authorname}")
    private String authorNameSkipList;

    @Override
    public void initialize(AuthorNameSkipValidator contactNumber) {
    }

    @Override
    public boolean isValid(String authorName, ConstraintValidatorContext constraintValidatorContext) {

        log.info("authorNameSkipList : {}", authorNameSkipList);
        log.info("authorName : {}", authorName);
        boolean isValid = false;

        if(authorName == null || authorName.isEmpty() || authorNameSkipList.toLowerCase().indexOf(authorName.toLowerCase()) == -1)
            isValid = true;

        log.info("isValid : {}", isValid);

        return isValid;
    }
}
