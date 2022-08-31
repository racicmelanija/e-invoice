import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { City } from 'src/app/models/city.model';
import { Country } from 'src/app/models/country.model';
import { CompanyService } from 'src/app/services/company.service';
import { EmploymentService } from 'src/app/services/employment.service';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-register-employee',
  templateUrl: './register-employee.component.html',
  styleUrls: ['./register-employee.component.scss']
})
export class RegisterEmployeeComponent implements OnInit {
  registrationForm!: FormGroup;

  constructor(private employmentService: EmploymentService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  private initializeForm(): void{
    this.registrationForm = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
      phoneNumber: new FormControl('', [Validators.required]),
      role: new FormControl('', [Validators.required])
    });
  }

  onSubmit(): void {
    let employee = {
      firstName: this.registrationForm.get("firstName")?.value,
      lastName: this.registrationForm.get("lastName")?.value,
      email: this.registrationForm.get("email")?.value,
      role: this.registrationForm.get("role")?.value,
      companyId: 'd876450f-40b6-425a-b33e-d8e0ed597489'
    }

    this.employmentService.registerEmployee(employee).subscribe(
      data => {
        this.toastr.success("Successfully registered employee");
        this.registrationForm.reset();
      },
      error => {
        this.toastr.error(error.error.errorMessage, "Error registering employee");
      })
  }

}
