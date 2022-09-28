import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Select } from '@ngxs/store';
import { Observable } from 'rxjs';
import { InvoiceService } from 'src/app/services/invoice.service';
import { AppState } from 'src/app/shared/app.state';
import { UpdateInvoiceDialogComponent } from '../update-invoice-dialog/update-invoice-dialog.component';

@Component({
  selector: 'app-incoming-invoices-table',
  templateUrl: './incoming-invoices-table.component.html',
  styleUrls: ['./incoming-invoices-table.component.scss']
})
export class IncomingInvoicesTableComponent implements OnInit {
  totalRows = 0;
  pageSize = 5;
  currentPage = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  displayedColumns: string[] = ['companyTaxId', 'bankAccount', 'referenceNumber', 'status', 'total', 'update'];
  dataSource: MatTableDataSource<any> = new MatTableDataSource();
  
  @Select(AppState.getCompanyId) companyId$: Observable<String> | undefined;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  
  constructor(private invoiceService: InvoiceService, private dialog: MatDialog) { }

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
        this.invoiceService.getIncomingInvoices(companyId, this.currentPage, this.pageSize).subscribe(
          data => {
            this.dataSource.data = data.invoices;
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

  update(id: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      invoiceId: id
    }
    this.dialog.open(UpdateInvoiceDialogComponent, dialogConfig)
    .afterClosed()
    .subscribe(() => this.loadData());
  }

}
