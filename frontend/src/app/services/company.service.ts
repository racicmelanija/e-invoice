import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  baseUrl: string = environment.baseUrl + '/company-service/companies'

  constructor(private http: HttpClient) { }

  postCompany(company: any): Observable<any> {
    return this.http.post(this.baseUrl, company);
  }
}
