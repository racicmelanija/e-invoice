import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/models/user.model';
import { UserService } from '../../services/user.service';
import { RoleAssignmentDialogComponent } from './components/role-assignment-dialog/role-assignment-dialog.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  selectedButton: string = 'new-users';
  users: User[] = [];
  filteredUsers: User[] = [];

  constructor(private userService: UserService, private toastr: ToastrService, private dialog: MatDialog, private http: HttpClient) { }

  ngOnInit(): void {
    this.userService.getUsers().subscribe(
      data => {
        data.forEach((user: any) => {
          this.constructUser(user);
        })
      }, error =>{
        this.toastr.error(error.message, "Error");
    });
    
    //testing zone
    this.http.get("http://localhost:9090/company-service/").subscribe();
  }

  selectButton(selectedButton: string): void {
    this.selectedButton = selectedButton;
    this.filterUsers();
  }

  filterUsers() {
    if(this.selectedButton == 'new-users'){
      this.filteredUsers = this.users.filter(user => user.roles.length == 0);
    } else if(this.selectedButton == 'users-with-roles'){
      this.filteredUsers = this.users.filter(user => user.roles.length > 0);
    }
  }

  constructUser(user: any): any {
    return this.userService.getUserRoles(user.id).subscribe(
      data => {
        let roles = data.filter((obj: any) => obj.name.startsWith('ROLE_'));
        let userWithRoles = new User(user.id, user.email, user.firstName, user.lastName, roles);
        this.users.push(userWithRoles);
        this.filterUsers();
      }
    );
  }

  showDialog(userId: string): void {
    let selectedUser = this.users.filter(user => user.id === userId)[0];
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      userId: userId,
      roles: selectedUser.roles
    }
    this.dialog.open(RoleAssignmentDialogComponent, dialogConfig);
  }

}
