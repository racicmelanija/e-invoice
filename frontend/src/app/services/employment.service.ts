import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmploymentService {
  baseUrl: string = environment.baseUrl + '/company-service/';

  constructor(private http: HttpClient) { }

  registerEmployee(employee: any): Observable<any> {
    return this.http.post(this.baseUrl + 'employees', employee);
  }

  getEmployments(): Observable<any> { 
    return this.http.get(this.baseUrl + 'employments');
  }

  getRole(companyId: String, userId: string): Observable<any> {
    return this.http.get(this.baseUrl + 'users/' + userId + '/roles?companyId=' + companyId);
  }

  createEmployment(employment: any): Observable<any> {
    return this.http.post(this.baseUrl + 'employments', employment);
  }
}
