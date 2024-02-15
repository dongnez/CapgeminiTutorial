import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { Prestamo } from 'src/app/prestamo/model/Prestamo';
import { PrestamoFilter } from 'src/app/prestamo/model/PrestamoFilter';
import { PrestamoPage } from 'src/app/prestamo/model/PrestamoPage';

@Injectable({
  providedIn: 'root'
})
export class PrestamoService {

  constructor(private http:HttpClient) { }

  getPrestamos(pageable:Pageable,filterParams?:PrestamoFilter):Observable<PrestamoPage>{
    return this.http.post<PrestamoPage>('http://localhost:8080/prestamo',{
      pageable:pageable,
      filterParams:filterParams
    });
  }

  createPrestamo(prestamo:Prestamo):Observable<any>{
    return this.http.post('http://localhost:8080/prestamo', prestamo);
  }

  getAllPrestamos():Observable<any>{
    return this.http.get('http://localhost:8080/prestamo');

  }

  savePrestamo(prestamo:Prestamo):Observable<any>{
    let url = 'http://localhost:8080/prestamo';
    if(prestamo.id != null) url += '/'+prestamo.id;
    return this.http.put<void>(url, prestamo);
  }

  deletePrestamo(id:number):Observable<void>{
    return this.http.delete<void>('http://localhost:8080/prestamo/'+id);
  }

}
