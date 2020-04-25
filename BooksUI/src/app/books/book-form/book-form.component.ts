import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { BooksService } from '../books.service';
import { Book } from '../model/book';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.scss']
})
export class BookFormComponent implements OnInit {

  bookFormGroup = this.fb.group({
    bookid: [0],
    bookName: [''],
    authorName: [''],
    price: ['0.00'],
    totalPage: [0]
  });

  book: Book;

  constructor(private fb: FormBuilder,
    private booksService: BooksService) { }

    ngOnInit(): void {

    }

  submitNewBook(event) {
    this.book = new Book(this.bookFormGroup.value);
    this.booksService.book = this.book;
    this.bookFormGroup.reset();
  }
}
