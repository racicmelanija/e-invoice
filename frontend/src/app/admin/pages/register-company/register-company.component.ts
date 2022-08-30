import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { City } from 'src/app/models/city.model';
import { Country } from 'src/app/models/country.model';
import { CompanyService } from '../../services/company.service';
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

  constructor(private locationService: LocationService, private companyService: CompanyService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.initializeForm();

    this.locationService.getCountries().subscribe(
      data => {
        this.countries = data.countries;
      }
    )
    this.locationService.getCities().subscribe(
      data => {
        this.cities = data.cities;
        this.filteredCities = data.cities;
      }
    )
  }

  onSubmit(): void {
    let company = {
      companyName: this.registrationForm.get("companyName")?.value,
      taxIdentificationNumber: this.registrationForm.get("taxIdentificationNumber")?.value,
      companyRegistrationNumber: this.registrationForm.get("companyRegistrationNumber")?.value,
      phoneNumber: this.registrationForm.get("phoneNumber")?.value,
      address: this.registrationForm.get("address")?.value,
      cityId: this.cities.filter(c => c.name == this.registrationForm.get("city")?.value)[0].id, 
      localCurrencyBankAccount: this.registrationForm.get("localCurrencyAccount")?.value,
      bankingFormat: this.registrationForm.get("eBankingFormat")?.value,
      foreignCurrencyBankAccount: this.registrationForm.get("foreignCurrencyAccount")?.value,
      currency: this.registrationForm.get("currency")?.value
    }

    this.companyService.postCompany(company).subscribe(
      data => {
        this.toastr.success("Successfully registered company");
        this.registrationForm.reset();
      },
      error => {
        this.toastr.error(error.error.errorMessage, "Error registering company");
      })
  }

  onCountryChanged() {
    this.registrationForm.controls.city.setValue('');
  }

  private initializeForm(): void{
    this.registrationForm = new FormGroup({
      companyName: new FormControl('', [Validators.required]),
      taxIdentificationNumber: new FormControl('', [Validators.required]),
      companyRegistrationNumber : new FormControl('', [Validators.required]),
      city : new FormControl('', [Validators.required]),
      country : new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
      phoneNumber: new FormControl('', [Validators.required]),
      localCurrencyAccount: new FormControl(''),
      eBankingFormat: new FormControl(''),
      foreignCurrencyAccount: new FormControl(''),
      currency: new FormControl('')
    });
  }

}
