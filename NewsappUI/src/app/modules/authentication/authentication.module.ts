import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { AuthenticationService } from './authentication.service';
import { MatInputModule, MatSnackBarModule, MatButtonModule, MatCardModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [RegisterComponent, LoginComponent],
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    FormsModule,
    MatInputModule,
    RouterModule,
  ],
  providers: [AuthenticationService],
  exports: [RegisterComponent, LoginComponent]
})
export class AuthenticationModule { }
