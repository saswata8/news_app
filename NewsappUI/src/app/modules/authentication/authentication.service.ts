import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as jwt_decode from 'jwt-decode';
export const TOKEN_NAME : string = 'jwt_token';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  springEndpoint : string;
  //token : string;

  constructor(private httpClient : HttpClient) {
    this.springEndpoint = 'http://localhost:8089/user';
   }

   registerUser(newUser)
   {
      const url = this.springEndpoint + '/register';
      return this.httpClient.post(url, newUser, {responseType:'text'});
   }

   loginUser(user)
   {
     console.log("Inside login service");
    const url = this.springEndpoint + '/login';
    return this.httpClient.post(url, user);
   }

   setToken(token : string)
    {
      return localStorage.setItem(TOKEN_NAME,token);
    }

    getToken()
    {
      return localStorage.getItem(TOKEN_NAME);
    }

    deleteToken()
    {
      return localStorage.removeItem(TOKEN_NAME);
    }

    isTokenExpired(token?: string) : boolean
    {
      if(!token)
        token = this.getToken();
      if(!token)
        return true;
      const date = this.getTokenExpirationDate(token);

      if(date == undefined || date == null)
        return false;
      
      return !(date.valueOf() > new Date().valueOf());
    }

    getTokenExpirationDate(token: String): Date
    {
      const decoded = jwt_decode(token);

      if(decoded.exp == undefined)
        return null;
      
      const date = new Date();
      date.setUTCSeconds(decoded.exp);
      return date;
    }
}
