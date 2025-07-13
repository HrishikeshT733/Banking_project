


import { Component } from '@angular/core';
import { AuthService } from '../login.service';
import { Router } from '@angular/router';
import { LoginResponse } from '../LoginResponse';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username = '';
  password = '';
  otp = '';
  loginMessage = '';
  loginClass = '';
  otpStep = false;
loginresponse!:LoginResponse;
 constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    if (!this.username || !this.password) {
      this.loginMessage = 'Please enter both username and password.';
      this.loginClass = 'error-message';
      return;
    }

    this.authService.login(this.username, this.password).subscribe({
      next: (res:LoginResponse) => {
        this.loginresponse=res;
        if (res.otpRequired) {
          // Admin login – OTP required
          this.otpStep = true;
          this.loginMessage = 'OTP sent to your registered mobile number.';
          this.loginClass = 'success-message';
        } else if (res.token) {
          // User login (no OTP)
          this.authService.storeToken(res.token);
          this.authService.storeUsername(this.username);

          if (this.authService.isTokenValid()) {
            this.loginMessage = 'Login successful!';
            this.loginClass = 'success-message';
            setTimeout(() => this.router.navigate(['/Home']), 1000);
          } else {
            this.loginMessage = 'Invalid token received.';
            this.loginClass = 'error-message';
          }
        } else {
          this.loginMessage = 'Unexpected response from server.';
          this.loginClass = 'error-message';
        }
      },
      error: () => {
        this.loginMessage = 'Login failed. Please check your credentials.';
        this.loginClass = 'error-message';
      }
    });
  }

  verifyOtp() {
    if (!this.otp) {
      this.loginMessage = 'Please enter the OTP.';
      this.loginClass = 'error-message';
      return;
    }
   if(this.loginresponse?.account?.phoneNumber){
    const phoneNumber=this.loginresponse.account?.phoneNumber;
 

    this.authService.verifyOtp(this.loginresponse.account?.phoneNumber, this.otp).subscribe({
      next: (res) => {
        if (res.token) {
          this.authService.storeToken(res.token);
          this.authService.storeUsername(this.username);

          if (this.authService.isTokenValid()) {
            this.loginMessage = 'Login successful!';
            this.loginClass = 'success-message';
            setTimeout(() => this.router.navigate(['/Home']), 1000);
          } else {
            this.loginMessage = 'Invalid token received after OTP.';
            this.loginClass = 'error-message';
          }
        } else {
          this.loginMessage = 'OTP verification failed.';
          this.loginClass = 'error-message';
        }
      },
      error: () => {
        this.loginMessage = 'Invalid OTP.';
        this.loginClass = 'error-message';
      }
    });
  }
}
}


// import { Component } from '@angular/core';
// import { AuthService } from '../login.service';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrl: './login.component.css'
// })
// export class LoginComponent {
//   username = '';
//   password = '';
//   otp = '';
//   loginMessage = '';
//   loginClass = '';
//   otpStep = false;

//   constructor(private authService: AuthService, private router: Router) { }

//   onSubmit() {
//     if (!this.username || !this.password) {
//       this.loginMessage = 'Please enter both username and password.';
//       this.loginClass = 'error-message';
//       return;
//     }

//     this.authService.login(this.username, this.password).subscribe({
//       next: (res) => {
//         if (res.token) {
//           // User login (no OTP)
//           this.authService.storeToken(res.token);
//           this.authService.storeUsername(this.username);

//           if (this.authService.isTokenValid()) {
//             this.loginMessage = 'Login successful!';
//             this.loginClass = 'success-message';
//             setTimeout(() => this.router.navigate(['/Home']), 1000);
//           } else {
//             this.loginMessage = 'Invalid token received.';
//             this.loginClass = 'error-message';
//           }
//         } else {
//           // Admin login – OTP required
//           this.otpStep = true;
//           this.loginMessage = 'OTP sent to your registered mobile number.';
//           this.loginClass = 'success-message';
//         }
//       },
//       error: () => {
//         this.loginMessage = 'Login failed. Please check your credentials.';
//         this.loginClass = 'error-message';
//       }
//     });
//   }

//   verifyOtp() {
//     if (!this.otp) {
//       this.loginMessage = 'Please enter the OTP.';
//       this.loginClass = 'error-message';
//       return;
//     }

//     this.authService.verifyOtp(this.username, this.otp).subscribe({
//       next: (res) => {
//         this.authService.storeToken(res.token);
//         this.authService.storeUsername(this.username);

//         if (this.authService.isTokenValid()) {
//           this.loginMessage = 'Login successful!';
//           this.loginClass = 'success-message';
//           setTimeout(() => this.router.navigate(['/Home']), 1000);
//         } else {
//           this.loginMessage = 'Invalid token received after OTP.';
//           this.loginClass = 'error-message';
//         }
//       },
//       error: () => {
//         this.loginMessage = 'Invalid OTP.';
//         this.loginClass = 'error-message';
//       }
//     });
//   }
// }
