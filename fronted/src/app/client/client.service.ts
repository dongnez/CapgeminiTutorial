import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Client } from 'src/app/client/model/Client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http:HttpClient) { }

  getClients(): Observable<Client[]> {
      return this.http.get<Client[]>('http://localhost:8080/client');
  }

  saveClients(category: Client): Observable<Client> {
        let url = 'http://localhost:8080/client';
        if (category.id != null) url += '/'+category.id;

        return this.http.put<Client>(url, category).pipe(
          catchError(this.handleError)
        )
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // Return an observable with a user-facing error message.
    return throwError(() => new Error('test'))
  };


  deleteClient(idCategory : number): Observable<any> {
    return this.http.delete('http://localhost:8080/client/'+idCategory);
  }  
}
