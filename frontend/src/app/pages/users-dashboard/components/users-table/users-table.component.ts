import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Select } from '@ngxs/store';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import { AppState } from 'src/app/shared/app.state';
import { AddRoleDialogComponent } from '../add-role-dialog/add-role-dialog.component';

@Component({
  selector: 'app-users-table',
  templateUrl: './users-table.component.html',
  styleUrls: ['./users-table.component.scss']
})
export class UsersTableComponent implements OnInit {
  totalRows = 0;
  pageSize = 5;
  currentPage = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'add'];
  dataSource: MatTableDataSource<any> = new MatTableDataSource();
  search: string = '';
  
  @Select(AppState.getCompanyId) companyId$: Observable<String> | undefined;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  
  constructor(private userService: UserService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadData();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  loadData() {
    this.userService.getUsers(this.search, this.currentPage * this.pageSize, this.pageSize).subscribe(
      data => {
        this.dataSource = data;
        setTimeout(() => {
          this.paginator.pageIndex = this.currentPage;
          this.userService.getUserCount().subscribe(
            data => {
              this.paginator.length = data;
            }
          )
        })
      }
    )
  }

  pageChanged(event: PageEvent) {
    console.log({ event });
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.loadData();
  }

  searchUsers() {
    this.loadData();
  }

  showAddRoleDialog(userId: string) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      userId: userId
    }
    this.dialog.open(AddRoleDialogComponent, dialogConfig);
  }

}
