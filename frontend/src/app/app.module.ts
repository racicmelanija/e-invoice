import { HttpClientModule } from '@angular/common/http';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { initializer } from 'src/utils/app.init';
import { MatDialogModule } from "@angular/material/dialog";
import { MatTableModule } from "@angular/material/table";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatFormFieldModule  } from "@angular/material/form-field";
import { MatInputModule   } from "@angular/material/input";
import { MatIconModule } from '@angular/material/icon'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/layout/headers/header/header.component';
import { PrimaryButtonComponent } from './shared/components/primary-button/primary-button.component';
import { SecondaryButtonComponent } from './shared/components/secondary-button/secondary-button.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RolesToStringPipe } from './shared/pipes/roles-to-string.pipe';
import { RegisterCompanyComponent } from './pages/companies-dashboard/components/register-company/register-company.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CompaniesDashboardComponent } from './pages/companies-dashboard/companies-dashboard.component';
import { UsersDashboardComponent } from './pages/users-dashboard/users-dashboard.component';
import { RegisterEmployeeComponent } from './pages/users-dashboard/components/register-employee/register-employee.component';
import { NgxsModule } from '@ngxs/store';
import { NgxsReduxDevtoolsPluginModule } from '@ngxs/devtools-plugin';
import { AppState } from './shared/app.state';
import { ClientsTableComponent } from './pages/companies-dashboard/components/clients-table/clients-table.component';
import { CompaniesTableComponent } from './pages/companies-dashboard/components/companies-table/companies-table.component';
import { UsersTableComponent } from './pages/users-dashboard/components/users-table/users-table.component';
import { AddRoleDialogComponent } from './pages/users-dashboard/components/add-role-dialog/add-role-dialog.component';
import { InvoicesDashboardComponent } from './pages/invoices-dashboard/invoices-dashboard.component';
import { CreateInvoiceComponent } from './pages/invoices-dashboard/components/create-invoice/create-invoice.component';
import { OutgoingInvoicesTableComponent } from './pages/invoices-dashboard/components/outgoing-invoices-table/outgoing-invoices-table.component';
import { IncomingInvoicesTableComponent } from './pages/invoices-dashboard/components/incoming-invoices-table/incoming-invoices-table.component';
import { UpdateInvoiceDialogComponent } from './pages/invoices-dashboard/components/update-invoice-dialog/update-invoice-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PrimaryButtonComponent,
    SecondaryButtonComponent,
    RolesToStringPipe,
    RegisterCompanyComponent,
    CompaniesDashboardComponent,
    UsersDashboardComponent,
    RegisterEmployeeComponent,
    ClientsTableComponent,
    CompaniesTableComponent,
    UsersTableComponent,
    AddRoleDialogComponent,
    InvoicesDashboardComponent,
    CreateInvoiceComponent,
    OutgoingInvoicesTableComponent,
    IncomingInvoicesTableComponent,
    UpdateInvoiceDialogComponent,
  ],
  imports: [
    FormsModule,
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule, 
    KeycloakAngularModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    MatDialogModule,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    NgxsModule.forRoot([
      AppState
    ]),
    NgxsReduxDevtoolsPluginModule.forRoot()
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
  entryComponents: []
})
export class AppModule { }
