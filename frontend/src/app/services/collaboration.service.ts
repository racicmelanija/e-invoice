import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CollaborationService {
  baseUrl: string = environment.baseUrl + '/company-service/';

  constructor(private http: HttpClient) { }

  getClients(companyId: String, page: number, size: number): Observable<any> {
    return this.http.get(this.baseUrl + 'collaborations?companyId=' + companyId + '&page=' + page + '&size=' + size);
  }
}
