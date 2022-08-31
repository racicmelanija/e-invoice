import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  baseUrl: string = environment.baseUrl + '/company-service/';

  constructor(private http: HttpClient) { }

  getCountries(): Observable<any> {
    return this.http.get<any>(this.baseUrl + 'countries');
  }

  getCities(): Observable<any> {
      return this.http.get<any>(this.baseUrl + 'cities');
  }
}
