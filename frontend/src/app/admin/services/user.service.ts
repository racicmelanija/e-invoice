import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl: string = 'http://localhost:8080/admin/realms/e-invoice/users'

  constructor(private http: HttpClient) { }

  getUsers(): Observable<any> {
    let params = new HttpParams()
    .set('first', 0)
    .set('max', 40);
    return this.http.get(this.baseUrl, {params});
  }

  getUserRoles(id: string): Observable<any> {
    return this.http.get(this.baseUrl + '/' + id + '/role-mappings/realm');
  }

  postUserRoles(id: string, roles: any[]): Observable<any> {
    return this.http.post(this.baseUrl + '/' + id + '/role-mappings/realm', roles);
  }

  deleteUserRoles(id: string, roles: any[]): Observable<any> {
    let options = {
      body: roles
    }
    return this.http.delete(this.baseUrl + '/' + id + '/role-mappings/realm', options);
  }
}
