import { HttpClientModule } from '@angular/common/http';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { initializer } from 'src/utils/app.init';
import { MatDialogModule } from "@angular/material/dialog";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminHeaderComponent } from './shared/layout/headers/admin-header/admin-header.component';
import { UsersComponent } from './admin/pages/users/users.component';
import { PrimaryButtonComponent } from './shared/components/primary-button/primary-button.component';
import { SecondaryButtonComponent } from './shared/components/secondary-button/secondary-button.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RolesToStringPipe } from './shared/pipes/roles-to-string.pipe';
import { RoleAssignmentDialogComponent } from './admin/pages/users/components/role-assignment-dialog/role-assignment-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminHeaderComponent,
    UsersComponent,
    PrimaryButtonComponent,
    SecondaryButtonComponent,
    RolesToStringPipe,
    RoleAssignmentDialogComponent
  ],
  imports: [
    BrowserModule,
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
