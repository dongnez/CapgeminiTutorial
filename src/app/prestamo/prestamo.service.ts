import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { Prestamo } from 'src/app/prestamo/model/Prestamo';
import { PrestamoPage } from 'src/app/prestamo/model/PrestamoPage';

@Injectable({
  providedIn: 'root'
})
export class PrestamoService {

  constructor(private http:HttpClient) { }

  getPrestamos(pageable:Pageable):Observable<PrestamoPage>{
    return this.http.post<PrestamoPage>('http://localhost:8080/prestamo',{
      pageable:pageable
    });
  }

  createPrestamo(prestamo:Prestamo):Observable<any>{
    return this.http.post('http://localhost:8080/prestamo', prestamo);
  }

  getAllPrestamos():Observable<any>{
    return this.http.get('http://localhost:8080/prestamo');

  }

  savePrestamo(prestamo:Prestamo):Observable<void>{
    let url = 'http://localhost:8080/prestamo';
    if(prestamo.id != null) url += '/'+prestamo.id;
    return this.http.put<void>(url, prestamo);
  }

}
