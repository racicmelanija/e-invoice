import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { City } from 'src/app/models/city.model';
import { Country } from 'src/app/models/country.model';
import { LocationService } from '../../services/location.service';

@Component({
  selector: 'app-register-company',
  templateUrl: './register-company.component.html',
  styleUrls: ['./register-company.component.scss']
})
export class RegisterCompanyComponent implements OnInit {
  registrationForm!: FormGroup;
  countries: Country[] = [];
  cities: City[] = [];
  filteredCities: City[] = [];

  constructor(private locationService: LocationService) { }

  ngOnInit(): void {
    this.initializeForm();

    this.locationService.getCountries().subscribe(
      data => {
        this.countries = data;
      }
    )
    this.locationService.getCities().subscribe(
      data => {
        this.cities = data;
        this.filteredCities = data;
      }
    )
  }

  onSubmit(): void {}

  onCountryChanged() {
    this.filteredCities = this.cities.filter(c => c.country.name == this.registrationForm.get('country')?.value);
    this.registrationForm.controls.city.setValue('');
  }

  private initializeForm(): void{
    this.registrationForm = new FormGroup({
      companyName: new FormControl('', [Validators.required]),
      taxIdentificationNumber: new FormControl('', [Validators.required, Validators.minLength(9), Validators.maxLength(9)]),
      companyRegistrationNumber : new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(8)]),
      city : new FormControl('', [Validators.required]),
      country : new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
      phoneNumber: new FormControl('', [Validators.required]),
      localCurrencyAccount: new FormControl(''),
      foreignCurrencyAccount: new FormControl(''),
    });
  }

}
