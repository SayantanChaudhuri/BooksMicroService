import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { BooksService } from './books.service';
import { Book } from './model/book';
import { Table } from 'primeng/table/table';
import { Observable, Subscription } from 'rxjs';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss']
})
export class BooksComponent implements OnInit, OnDestroy {

  booksList: Book[] = [{ bookId: 0, bookName: '', authorName: '', totalPage: 0, price: 0.00 }];
  loading: boolean = true;
  newBook: Book;
  first = 0;
  last = 0;
  totalRecords = 0;

  @ViewChild('dt') table: Table;

  private bookObserveable: Observable<Book>;
  private bookModalSubscription: Subscription;
  modalDisplay: boolean = false;

  constructor(private booksService: BooksService, private messageService: MessageService) {

    this.getAllBooks();
    this.bookObserveable = this.booksService.bookObservable;
    this.bookModalSubscription = this.bookObserveable.subscribe(book => {
      this.modalDisplay = false;
      this.saveBook(book);
    })
  }

  ngOnInit() {
  }

  ngOnDestroy() {
    this.bookModalSubscription.unsubscribe();
  }

  getAllBooks() {
    this.loading = true;
    this.booksService
      .getAllBooks()
      .subscribe(
        response => {
          console.log(response);
          this.booksList = response.books;
          this.loading = false;
        },
        err => {
          console.error(err);
          this.loading = false;
          this.messageService.add({severity:'error', summary: 'Success', detail:'Error: Books couldn\'t load. Try later'});
        }
      )
  }

  saveBook(book: Book) {
    this.loading = true;
    this.booksService
        .saveBook(book)
        .subscribe(
          response => {
            this.booksList = response.books;
            this.loading = false;
            this.messageService.add({severity:'success', summary: 'Success', detail:'Book successfully saved.'});
          },
          err => {
            console.error(err);
            this.loading = false;
            this.messageService.add({severity:'error', summary: 'Success', detail:'Error: Book couldn\'t saved. Try later'});
          }
        )
  }

  showNewBookModal(event) {
    this.modalDisplay = true;
  }
}
