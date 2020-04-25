import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { BooksComponent } from './books.component';
import { BooksService } from './books.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { of, Observable } from 'rxjs';
import { Books } from './model/books';
import { Book } from './model/book';

describe('BooksComponent', () => {
  let component: BooksComponent;
  let fixture: ComponentFixture<BooksComponent>;
  let booksObservable: Observable<Books> = of({
    "books": [
      {
        "bookId": 2,
        "bookName": "Head First Design Patterns",
        "authorName": "Eric Freeman, Bert Bates, Kathy Sierra ",
        "totalPage": 676,
        "price": 706.65
      }],
    "totalBooks": 1,
    "totalPageCount": 1
  });

  let book: Book = {
    "authorName": "Anghel Leonard",
    "bookId": 1,
    "bookName": "Java Coding Problems",
    "price": 571.76,
    "totalPage": 816
  };

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BooksComponent],
      imports: [
        HttpClientTestingModule,
        ToastModule
      ],
      providers: [
        BooksService,
        MessageService
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call getAllBooks', async(inject([BooksService], (booksService: BooksService) => {
    spyOn(booksService, 'getAllBooks').and.returnValue(booksObservable);

    fixture.detectChanges();

    fixture.whenStable().then(() => {
      expect(component.booksList.length).toBeGreaterThan(0);
    });
  })));

  it('should call saveBook', async(inject([BooksService], (booksService: BooksService) => {
    spyOn(booksService, 'saveBook')
      .withArgs(book)
      .and.returnValue(booksObservable);

    fixture.detectChanges();

    fixture.whenStable().then(() => {
      expect(component.booksList.length).toBeGreaterThan(0);
    });
  })));
});
