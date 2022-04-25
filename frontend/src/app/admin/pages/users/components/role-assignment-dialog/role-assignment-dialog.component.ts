import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RoleService } from 'src/app/admin/services/role.service';
import { UserService } from 'src/app/admin/services/user.service';

@Component({
  selector: 'app-role-assignment-dialog',
  templateUrl: './role-assignment-dialog.component.html',
  styleUrls: ['./role-assignment-dialog.component.scss']
})
export class RoleAssignmentDialogComponent implements OnInit {
  userId: string = '';
  roles: any[] = [];
  assignRolesForm!: FormGroup;
  systemRoles: any[] = [];

  constructor(private dialogRef: MatDialogRef<RoleAssignmentDialogComponent>, @Inject(MAT_DIALOG_DATA) data: any, private roleService: RoleService, private userService: UserService, private toastr: ToastrService) {
    this.userId = data.userId;
    this.roles = data.roles;
    this.initForm();
  }

  ngOnInit(): void {
    this.roleService.getAllRoles().subscribe(
      data => {
        this.systemRoles = data;
        this.systemRoles = this.systemRoles.filter((role: any) => role.name.startsWith('ROLE_'));
        this.checkActiveRole();
      }
    );
  }

  roleChanged(event: any, value: string) {
    const selectedRoles = (this.assignRolesForm.controls.roles as FormArray);
    if (event.target.checked) {
      selectedRoles.push(new FormControl(value));
    } else {
      const index = selectedRoles.controls
        .findIndex(x => x.value === value);
      selectedRoles.removeAt(index);
    }
    console.log(this.roles)
  }

  close(){
    this.dialogRef.close();
  }

  confirmRoleSelection(){
    let newRoles = this.assignRolesForm.get('roles')?.value.filter((x: any) => this.roles.every((r: any) => r.id !== x.id));
    this.userService.postUserRoles(this.userId, newRoles).subscribe(
      data => {
        this.toastr.success("Roles added successfully", "Success");
        this.close();
      }, 
      error => {
        this.toastr.error(error.message, "Error");
      });
    
    let rolesForDeleting = this.roles.filter((x: any) => this.assignRolesForm.get('roles')?.value.every((r: any) => r.id !== x.id));
    this.userService.deleteUserRoles(this.userId, rolesForDeleting).subscribe(
      data => {
        this.toastr.success("Roles removed successfully", "Success");
        this.close();
        window.location.reload();
      }, 
      error => {
        this.toastr.error(error.message, "Error");
      });
  }

  private initForm() {
    this.assignRolesForm = new FormGroup({
      roles: new FormArray([])
    })
  }

  private checkActiveRole() {
    const selectedRoles = (this.assignRolesForm.controls.roles as FormArray);
    for(let i = 0; i < this.roles.length; i++) {
      let role = this.systemRoles.find(r => r.id === this.roles[i].id);
      selectedRoles.push(new FormControl(role));
    }
  }

}
