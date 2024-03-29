import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Select } from '@ngxs/store';
import { Observable } from 'rxjs';
import { CollaborationService } from 'src/app/services/collaboration.service';
import { AppState } from 'src/app/shared/app.state';

@Component({
  selector: 'app-clients-table',
  templateUrl: './clients-table.component.html',
  styleUrls: ['./clients-table.component.scss']
})
export class ClientsTableComponent implements OnInit {
  totalRows = 0;
  pageSize = 5;
  currentPage = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  displayedColumns: string[] = ['clientName', 'clientTaxIdentificationNumber', 'clientRegistrationNumber', 'phoneNumber'];
  dataSource: MatTableDataSource<any> = new MatTableDataSource();
  
  @Select(AppState.getCompanyId) companyId$: Observable<String> | undefined;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  
  constructor(private collaborationService: CollaborationService) { }

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
        this.collaborationService.getClients(companyId, this.currentPage, this.pageSize).subscribe(
          data => {
            this.dataSource.data = data.collaborations;
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

}
