import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, Validators, ReactiveFormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { HttpClientModule,HttpClient } from '@angular/common/http'; // Import HttpClientModule here

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [RouterLink, FormsModule, NgIf, HttpClientModule, ReactiveFormsModule], // Add HttpClientModule here
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  signupForm!: FormGroup;

  public showToast = false;

  constructor(private fb: FormBuilder,private http:HttpClient) {}

  ngOnInit() {
    this.signupForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]]
    });
  }

  signUp() {
    console.log(this.signupForm.value);

    this.http.post('http://localhost:8081/api/auth/signup', this.signupForm.value)
      .subscribe({
        next: (response) => {
          console.log('Registration successful:', response);
          this.showToast = true; // Show the toast
        setTimeout(() => (this.showToast = false), 3000);
        },
        error: (error) => {
          console.error('Registration failed:', error);
        }
      });
  }
}
