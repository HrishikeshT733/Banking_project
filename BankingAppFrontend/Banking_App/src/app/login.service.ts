import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { Observable } from 'rxjs';
import { LoginResponse } from './LoginResponse';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private usernameKey = 'username';
  private logoutTimer: any;

  constructor(private http: HttpClient, private router: Router) { }

login(username: string, password: string): Observable<LoginResponse> {
  return this.http.post<LoginResponse>(`${this.apiUrl}/login`, { username, password });
}
  verifyOtp(phoneNumber:string,otp:string){
return this.http.post<{ token: string }>(`${this.apiUrl}/verify-otp`, { phoneNumber, otp });
  }

  storeToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

   storeUsername(username: string) {
    localStorage.setItem(this.usernameKey, username);
  }

  getUsername(): string | null {
    return localStorage.getItem(this.usernameKey);
  }


  isTokenValid(): boolean {
    const token = this.getToken();
    if (!token) return false;

    try {
      const decoded: any = jwtDecode(token);
      const exp = decoded.exp;
      const currentTime = Math.floor(Date.now() / 1000);
      return exp > currentTime;
    } catch (err) {
      return false;
    }
  }
startTokenTimer(token: string) {
    const decoded: any = jwtDecode(token);
    const exp = decoded.exp;
    const expiresAt = exp * 1000; // Convert to milliseconds
    const timeout = expiresAt - Date.now();

    if (timeout > 0) {
      this.logoutTimer = setTimeout(() => {
        alert('Session expired. Please log in again.');
        this.logout();
      }, timeout);
    }
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
     if (this.logoutTimer) {
      clearTimeout(this.logoutTimer);
    }
    this.router.navigate(['/login']);
  }


  redirectIfAuthenticated() {
    if (this.isTokenValid()) {
      this.router.navigate(['/Home']);
    }
  }
}
