import { HttpClientModule } from '@angular/common/http';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { initializer } from 'src/utils/app.init';
import { MatDialogModule } from "@angular/material/dialog";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/layout/headers/header/header.component';
import { UsersComponent } from './pages/users/users.component';
import { PrimaryButtonComponent } from './shared/components/primary-button/primary-button.component';
import { SecondaryButtonComponent } from './shared/components/secondary-button/secondary-button.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RolesToStringPipe } from './shared/pipes/roles-to-string.pipe';
import { RoleAssignmentDialogComponent } from './pages/users/components/role-assignment-dialog/role-assignment-dialog.component';
import { RegisterCompanyComponent } from './pages/companies-dashboard/components/register-company/register-company.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CompaniesDashboardComponent } from './pages/companies-dashboard/companies-dashboard.component';
import { UsersDashboardComponent } from './pages/users-dashboard/users-dashboard.component';
import { RegisterEmployeeComponent } from './pages/users-dashboard/components/register-employee/register-employee.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    UsersComponent,
    PrimaryButtonComponent,
    SecondaryButtonComponent,
    RolesToStringPipe,
    RoleAssignmentDialogComponent,
    RegisterCompanyComponent,
    CompaniesDashboardComponent,
    UsersDashboardComponent,
    RegisterEmployeeComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule, 
    KeycloakAngularModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    MatDialogModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      multi: true,
      deps: [KeycloakService],
    },
  ],
  bootstrap: [AppComponent],
  entryComponents: [RoleAssignmentDialogComponent]
})
export class AppModule { }
