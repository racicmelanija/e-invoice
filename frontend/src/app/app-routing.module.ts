import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/utils/app.guard';
import { CompaniesDashboardComponent } from './pages/companies-dashboard/companies-dashboard.component';
import { UsersDashboardComponent } from './pages/users-dashboard/users-dashboard.component';

const routes: Routes = [
  {path: '', redirectTo: 'users', pathMatch: 'full'},
  {path: 'users', component: UsersDashboardComponent, canActivate: [AuthGuard], data: {roles: ['COMPANY_OWNER', 'ADMIN']}},
  {path: 'companies', component: CompaniesDashboardComponent, canActivate: [AuthGuard], data: {roles: ['COMPANY_OWNER']}},
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
