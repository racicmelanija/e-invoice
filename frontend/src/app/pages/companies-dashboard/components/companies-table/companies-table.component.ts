import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Select } from '@ngxs/store';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { CollaborationService } from 'src/app/services/collaboration.service';
import { CompanyService } from 'src/app/services/company.service';
import { AppState } from 'src/app/shared/app.state';

@Component({
  selector: 'app-companies-table',
  templateUrl: './companies-table.component.html',
  styleUrls: ['./companies-table.component.scss']
})
export class CompaniesTableComponent implements OnInit {
  totalRows = 0;
  pageSize = 5;
  currentPage = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  displayedColumns: string[] = ['companyName', 'companyTaxIdentificationNumber', 'companyRegistrationNumber', 'phoneNumber', 'add'];
  dataSource: MatTableDataSource<any> = new MatTableDataSource();
  search: string = '';
  
  @Select(AppState.getCompanyId) companyId$: Observable<String> | undefined;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private companyService: CompanyService, private collaborationService: CollaborationService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.loadData();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  loadData() {
    let companyId: String = "";
    this.companyId$?.subscribe(
      data => {
        companyId = data;
        this.companyService.getCompanies(companyId, this.search, this.currentPage, this.pageSize).subscribe(
          data => {
            this.dataSource.data = data.companies;
            setTimeout(() => {
              this.paginator.pageIndex = this.currentPage;
              this.paginator.length = data.totalElements;
            })
          }
        )
      }
    ) 
  }

  pageChanged(event: PageEvent) {
    console.log({ event });
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.loadData();
  }

  searchCompanies() {
    this.loadData();
  }

  addClient(clientId: string) {
    let companyId: String = "";
    this.companyId$?.subscribe(
      data => {
        companyId = data
      }
    )
    this.collaborationService.postCollaboration({ companyId: companyId, clientId: clientId }).subscribe(
      data => {
        this.toastr.success("Successfully added client");
        this.loadData();
      },
      error => {
        this.toastr.error(error.error.errorMessage, "Error while adding client");
      }
    )
  }

}
