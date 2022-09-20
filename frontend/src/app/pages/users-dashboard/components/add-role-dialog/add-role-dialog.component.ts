import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Select } from '@ngxs/store';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { EmploymentService } from 'src/app/services/employment.service';
import { AppState } from 'src/app/shared/app.state';

@Component({
  selector: 'app-add-role-dialog',
  templateUrl: './add-role-dialog.component.html',
  styleUrls: ['./add-role-dialog.component.scss']
})
export class AddRoleDialogComponent implements OnInit {
  userId: string = '';
  assignRolesForm!: FormGroup;
  isCompanyOwner: boolean = false;

  @Select(AppState.getCompanyId) companyId$: Observable<String> | undefined;

  constructor(private dialogRef: MatDialogRef<AddRoleDialogComponent>, @Inject(MAT_DIALOG_DATA) data: any, private employmentService: EmploymentService, private toastr: ToastrService) { 
    this.userId = data.userId;
  }

  ngOnInit(): void {
    this.initForm('');
    let companyId: String = "";
    this.companyId$?.subscribe(
      data => {
        companyId = data
      }
    ) 
    this.employmentService.getRole(companyId, this.userId).subscribe(
      data => {
        this.initForm(data.role);
      })
    
  }

  addEmployeeOrRole() {
    let companyId: String = "";
    this.companyId$?.subscribe(
      data => {
        companyId = data
      }
    ) 
    this.employmentService.createEmployment({companyId: companyId, userId: this.userId, role: this.assignRolesForm.get('role')?.value}).subscribe(
      data => {
        this.toastr.success("Successfully added role to the user in the company you currently have selected");
        this.close();
      },
      error => {
        this.toastr.error(error.error.errorMessage, "Error adding or updating the role");
      }
    )
  }

  close(){
    this.dialogRef.close();
  }

  private initForm(role: any) {
    if(role == 'COMPANY_OWNER') {
      this.isCompanyOwner = true;
    } else {
      this.isCompanyOwner = false;
    }
    this.assignRolesForm = new FormGroup({
      role: new FormControl(role, [Validators.required])
    })
  }

}
