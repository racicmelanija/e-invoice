import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/utils/app.guard';
import { UsersComponent } from './admin/pages/users/users.component';

const routes: Routes = [
  {path: 'users', component: UsersComponent, canActivate: [AuthGuard], data: {roles: ['ROLE_ADMIN']}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
