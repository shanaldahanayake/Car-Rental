import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const BASE_URL="http://localhost:8081";

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private http:HttpClient) { }

  register(signUpRequest:any){
    return this.http.post(`${BASE_URL}/api/auth/signup`,signUpRequest);
  }
}
