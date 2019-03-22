import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../authentication.service';
import { Router } from '@angular/router';
import { User } from '../../User';

@Component({
  selector: 'authentication-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user : User;
  constructor(private authService : AuthenticationService, private router : Router) { 
    this.user = new User();
  }

  ngOnInit() {
  }

  onLogin()
  {
    console.log("Inside login");
    this.authService.loginUser(this.user).subscribe(data=>{
      if(data['token'])
      {
        console.log(data);
        this.authService.setToken(data['token']);
        this.router.navigate(['/news/popular']);
      }
    });
  }

}
