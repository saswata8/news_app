import { NgModule } from '@angular/core';
import { Routes, RouterModule } from "@angular/router";
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';

const authRoutes : Routes = [
    {
        path : '',
        children : [
            {
                path : '',
                redirectTo : '/login',
                pathMatch : 'full'
            },
            {
                path : 'register',
                component : RegisterComponent,
            },
            {
                path : 'login',
                component : LoginComponent,
            }
        ]
    }
];

@NgModule({
    imports:[RouterModule.forChild(authRoutes)],
    exports: [RouterModule]
})
export class AuthenticationRouterModule
{
    
}