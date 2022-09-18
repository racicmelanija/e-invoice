import { Component, OnInit } from '@angular/core';
import { Store } from '@ngxs/store';
import { KeycloakService } from 'keycloak-angular';
import { EmploymentService } from './services/employment.service';
import { SetCompany } from './shared/app.actions';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'e-invoice';

  constructor(private employmentService: EmploymentService, private store: Store){}

  ngOnInit(): void {
    this.employmentService.getEmployments().subscribe(
      data => {
        this.store.dispatch([
          new SetCompany(data.employments[0])
        ])
      })
  }

}
