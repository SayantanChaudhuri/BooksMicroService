export class Book {
  bookId?: number;
  bookName: string;
  authorName: string;
  totalPage: number;
  price: number;

  public constructor(init?: Partial<Book>) {
    Object.assign(this, init);
}
}