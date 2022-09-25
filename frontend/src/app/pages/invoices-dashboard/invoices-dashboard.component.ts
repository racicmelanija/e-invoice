import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-invoices-dashboard',
  templateUrl: './invoices-dashboard.component.html',
  styleUrls: ['./invoices-dashboard.component.scss']
})
export class InvoicesDashboardComponent implements OnInit {
  selectedButton: string = 'new-invoice';

  constructor() { }

  ngOnInit(): void {
  }

  selectButton(selectedButton: string): void {
    this.selectedButton = selectedButton;
  }

}
