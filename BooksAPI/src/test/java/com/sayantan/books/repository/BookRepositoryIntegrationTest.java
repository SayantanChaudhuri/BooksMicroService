package com.sayantan.books.repository;

import com.sayantan.books.model.BookEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

//    @Ignore
    @Test
    public void whenFindAll_thenReturnEmployee() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bookName"));

        Page<BookEntity> bookEntities = bookRepository.findAll(pageable);

        if(bookEntities.hasContent()) {
            assertThat("employeeCount",
                    bookEntities.getTotalElements(),
                    greaterThan(0L));
        } else {
            assertThat("employeeCount",
                    bookEntities.getTotalElements(),
                    equalTo(0L));
        }
    }
}
