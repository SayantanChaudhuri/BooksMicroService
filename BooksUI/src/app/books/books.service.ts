import { Injectable } from '@angular/core';
import { ApiService } from '../api.service';
import { Observable, Subject } from 'rxjs';
import { Books } from './model/books';
import { Book } from './model/book';

@Injectable({
  providedIn: 'root',
})
export class BooksService {
  constructor(private apiService: ApiService) {}

  private _book: Book;

  private _bookSubject: Subject <Book> = new Subject();

  getAllBooks(): Observable<Books> {
    return this.apiService.get('http://localhost:8080/api/getAllBooks');
  }

  saveBook(book: Book): Observable<Books> {
    return this.apiService.post('http://localhost:8080/api/saveBook', book);
  }

  public get book() : Book {
    return this._book;
  }

  public set book(book : Book) {
    this._book = book;
    this._bookSubject.next(this._book);
  }

  public get bookObservable() : Observable<Book> {
    return this._bookSubject.asObservable();
  }
}
