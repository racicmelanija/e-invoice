import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'frontend';
  role = '';

  constructor(private keycloakService: KeycloakService){}

  ngOnInit(): void {
    this.role = this.keycloakService.getUserRoles().filter(role => role.startsWith('ROLE_'))[0];
  }

}
