import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatToolbarModule, MatButtonModule, MatDialogModule, MatInputModule, MatFormFieldModule, MatIconModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Routes, RouterModule } from '@angular/router';
import { AuthenticationRouterModule } from './modules/authentication/authentication-router.module';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { NewsModule } from './modules/news/news.module';
import { AuthGuardService } from './auth-guard.service';
import { AuthenticationService } from './modules/authentication/authentication.service';

const appRoutes : Routes = [
  {
    path : '',
    redirectTo: '/login',
    pathMatch: 'full'
  }
]

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    FormsModule,
    NewsModule,
    AuthenticationRouterModule,
    AuthenticationModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [AuthGuardService, AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
