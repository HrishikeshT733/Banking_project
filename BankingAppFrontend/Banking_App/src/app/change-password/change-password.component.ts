import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ChangePasswordRequest } from './changePassword';
import { AuthService } from '../login.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-change-password',
  standalone: false,
  templateUrl: './change-password.component.html',
  styleUrl: './change-password.component.css'
})
export class ChangePasswordComponent {
  changePasswordRequest: ChangePasswordRequest = { oldPassword: '', newPassword: '' };
  message = '';
  success = false;
 username: string | null = '';
  constructor(private http: HttpClient,private authService:AuthService,private router:Router) {}


  ngOnInit() {
   
   this.username= this.authService.getUsername();

  }
  onChangePassword(): void {
    this.http.post('http://localhost:8080/api/auth/login/change-password', this.changePasswordRequest)
      .subscribe({
        next: (response) => {
        
          this.message = 'Password changed successfully!';
          this.success = true;
          
    setTimeout(() => {
     this.authService.logout(); 
    }, 1000);
              
        },
        error: (err) => {
        
          this.message = err.error?.message || 'Password change failed';
          this.success = false;
        }
      });
  }
  logout() {
    this.authService.logout(); 
    
              this.router.navigate(['/login']); // Redirect to LoginComponent
          
  }
}
