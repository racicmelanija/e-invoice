import { Component, HostListener, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.scss']
})
export class AdminHeaderComponent implements OnInit {
  isVisible: boolean = true;
  dropdownMenuVisible: boolean = false;
  isGlassEffect: boolean = false;
  selectedButton: string = 'users';
  _ = require('lodash');
  debouncedOnScroll = this._.debounce(() => this.toggleNavigationBackground(), 300, {})

  constructor(private keycloakService : KeycloakService) { }

  ngOnInit(): void {
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
}
