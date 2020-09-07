import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';
import { Veiculo } from 'src/model/veiculo';

const httpOptions = { headers: new HttpHeaders({'Content-Type': 'application/json'})};
const baseUrl = 'http://localhost:8080/veiculos';

@Injectable({providedIn: 'root'})
export class VeiculoService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(baseUrl);
  }

  get(id): Observable<any> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  findByDescricao(descricao): Observable<any> {
    return this.http.get(`${baseUrl}/find?descricao=${descricao}`);
  }

  create(data): Observable<any> {
    return this.http.post<Veiculo>(baseUrl, data);
  }

  update(id, data): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  sale(id, data): Observable<any> {
    return this.http.patch(`${baseUrl}/${id}`, data);
  }

  delete(id): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  distribuicaoAno(): Observable<any> {
    return this.http.get(`${baseUrl}/distrib_ano`);
  }

  distribuicaoMarca(): Observable<any> {
    return this.http.get(`${baseUrl}/distrib_marca`);
  }

  private handleError<T> (operation = 'operation', result?: T)
  {
    return (error: any): Observable<T> =>
    {
      console.error(error);
      return of(result as T);
    };
  }
}
