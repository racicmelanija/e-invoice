import { Component, HostListener, OnInit } from '@angular/core';
import { Store } from '@ngxs/store';
import { KeycloakService } from 'keycloak-angular';
import { EmploymentService } from 'src/app/services/employment.service';
import { SetCompany } from 'src/app/shared/app.actions';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isVisible: boolean = true;
  dropdownMenuVisible: boolean = false;
  isGlassEffect: boolean = false;
  selectedButton: string = 'users';
  _ = require('lodash');
  debouncedOnScroll = this._.debounce(() => this.toggleNavigationBackground(), 300, {})
  roles: any[] = [];
  employments: any[] = [];
  selectedCompany: any;

  constructor(private keycloakService : KeycloakService, private employmentService: EmploymentService, private store: Store) { }

  ngOnInit(): void {
    this.roles = this.keycloakService.getUserRoles();
    this.employmentService.getEmployments().subscribe(
      data => {
        this.employments = data.employments;
        this.selectedCompany = this.employments[0];
        this.updateState();
      })
  }

  @HostListener('window:scroll', ['$event'])
  onScroll() {
    this.debouncedOnScroll();
  }

  toggleDropdownMenu(): void{
    this.dropdownMenuVisible = !this.dropdownMenuVisible;
  }

  toggleNavigationBackground(): void{
    if(window.pageYOffset == 0)
      this.isGlassEffect = false;
    else
      this.isGlassEffect = true;
  }

  logout(): void {
    this.keycloakService.logout();
  }

  selectButton(selectedButton: string): void {
    this.selectedButton = selectedButton;
  }

  containsRole(roleName: string): boolean {
    return this.roles.some(role => role === roleName);
  }

  updateState(): void {
    this.store.dispatch([
      new SetCompany(this.selectedCompany)
    ])
  }
}
