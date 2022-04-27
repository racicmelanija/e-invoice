import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { City } from 'src/app/models/city.model';
import { Country } from 'src/app/models/country.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  baseUrl: string = environment.baseUrl + '/location/';

  constructor(private http: HttpClient) { }

  getCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(this.baseUrl + 'countries');
}

getCities(): Observable<City[]> {
    return this.http.get<City[]>(this.baseUrl + 'cities');
}
}
