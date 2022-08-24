import { Component, HostListener, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

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

  constructor(private keycloakService : KeycloakService) { }

  ngOnInit(): void {
    this.roles = this.keycloakService.getUserRoles();
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
}
