import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-companies-dashboard',
  templateUrl: './companies-dashboard.component.html',
  styleUrls: ['./companies-dashboard.component.scss']
})
export class CompaniesDashboardComponent implements OnInit {
  selectedButton: string = 'companies';

  constructor() { }

  ngOnInit(): void {
  }

  selectButton(selectedButton: string): void {
    this.selectedButton = selectedButton;
  }

}
