import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/utils/app.guard';
import { CompaniesDashboardComponent } from './pages/companies-dashboard/companies-dashboard.component';
import { InvoicesDashboardComponent } from './pages/invoices-dashboard/invoices-dashboard.component';
import { UsersDashboardComponent } from './pages/users-dashboard/users-dashboard.component';

const routes: Routes = [
  {path: '', redirectTo: 'invoices', pathMatch: 'full'},
  {path: 'invoices', component: InvoicesDashboardComponent, canActivate: [AuthGuard], data: {roles: ['COMPANY_OWNER', 'ADMIN', 'INCOMING_INVOICE_USER', 'OUTGOING_INVOICE_USER']}},
  {path: 'users', component: UsersDashboardComponent, canActivate: [AuthGuard], data: {roles: ['COMPANY_OWNER', 'ADMIN']}},
  {path: 'companies', component: CompaniesDashboardComponent, canActivate: [AuthGuard], data: {roles: ['COMPANY_OWNER']}},
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
