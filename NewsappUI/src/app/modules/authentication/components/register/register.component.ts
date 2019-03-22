import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { Router } from '@angular/router';
import { User } from '../../User';

@Component({
  selector: 'authentication-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser : User;

  constructor(private authService : AuthenticationService, private router : Router) { 
    this.newUser = new User();
  }

  ngOnInit() {
  }

  onRegister()
  {
    this.authService.registerUser(this.newUser).subscribe(data=>{
      this.router.navigate(['/login']);
    })
  }

}
