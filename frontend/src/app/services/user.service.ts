import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl: string = 'http://localhost:8080/admin/realms/e-invoice/users'

  constructor(private http: HttpClient) { }

  getUsers(search: string, offset: number, limit: number): Observable<any> {
    let params = new HttpParams()
    .set('search', search)
    .set('first', offset)
    .set('max', limit);
    return this.http.get(this.baseUrl, {params});
  }

  getUserCount(): Observable<any> {
    return this.http.get(this.baseUrl + '/count');
  }
}
