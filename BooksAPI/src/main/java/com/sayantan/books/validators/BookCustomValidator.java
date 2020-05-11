package com.sayantan.books.validators;

import com.sayantan.books.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCustomValidator implements Validator {

    @Value("${skiplist.bookname}")
    private String bookNameSkipList;

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        log.info("bookNameSkipList : {}", bookNameSkipList);
        log.info("book : {}", book);
        boolean isValid = false;

        if(book != null && book.getBookName() != null && !book.getBookName().isEmpty() && bookNameSkipList.toLowerCase().indexOf(book.getBookName().toLowerCase()) > -1)
            errors.rejectValue("bookName", "Invalid", new Object[]{"bookName"}, "Invalid book name");
    }
}
