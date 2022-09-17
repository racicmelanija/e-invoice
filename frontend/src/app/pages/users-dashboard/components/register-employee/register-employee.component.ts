import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Select } from '@ngxs/store';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { EmploymentService } from 'src/app/services/employment.service';
import { AppState, AppStateModel } from 'src/app/shared/app.state';

@Component({
  selector: 'app-register-employee',
  templateUrl: './register-employee.component.html',
  styleUrls: ['./register-employee.component.scss']
})
export class RegisterEmployeeComponent implements OnInit {
  registrationForm!: FormGroup;
  @Select(AppState.getCompanyId) companyId$: Observable<String> | undefined;

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
    let companyId;
    this.companyId$?.subscribe(
      data => {
        companyId = data
      }
    ) 
    let employee = {
      firstName: this.registrationForm.get("firstName")?.value,
      lastName: this.registrationForm.get("lastName")?.value,
      email: this.registrationForm.get("email")?.value,
      role: this.registrationForm.get("role")?.value,
      companyId: companyId
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
