import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Select } from '@ngxs/store';
import { Observable } from 'rxjs';
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
  displayedColumns: string[] = ['companyName', 'companyTaxIdentificationNumber', 'companyRegistrationNumber', 'phoneNumber'];
  dataSource: MatTableDataSource<any> = new MatTableDataSource();
  search: string = '';
  
  @Select(AppState.getCompanyId) companyId$: Observable<String> | undefined;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private companyService: CompanyService) { }

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
        companyId = data
      }
    )
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

  pageChanged(event: PageEvent) {
    console.log({ event });
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.loadData();
  }

  searchCompanies() {
    this.loadData();
  }

}
