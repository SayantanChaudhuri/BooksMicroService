import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { of, Observable } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ApiService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  get(requestUrl: string): any {
    console.log(requestUrl);
    return this.http.get(requestUrl)
      .pipe(
        tap(cases => console.log('fetched all books')),
        catchError(this.handleError('getAllBooks', [])));
  }

  post(requestUrl: string, requestBody: any, timeout?: number): any {
    return this.http.post(requestUrl, requestBody);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
