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

  getCompanies(notClientsWith: String, search: string, page: number, size: number): Observable<any> {
    return this.http.get(this.baseUrl + "?notClientsWith=" + notClientsWith + "&search=" + search + "&page=" + page + "&size=" + size);
  }

  getCompany(companyId: String): Observable<any> {
    return this.http.get(this.baseUrl + "/" + companyId);
  }
}
