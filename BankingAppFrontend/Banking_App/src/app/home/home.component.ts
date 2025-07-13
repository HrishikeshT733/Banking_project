import { Component,OnInit,OnDestroy} from '@angular/core';
import { AuthService } from '../login.service';
import { Router } from '@angular/router';

import { jwtDecode } from 'jwt-decode';
import { AccountService } from '../account.service';
import { Account } from '../account';
@Component({
  selector: 'app-login',
  standalone: false,
templateUrl: './home.component.html',
  styleUrl: './home.component.css' 
})
export class HomeComponent implements OnInit,OnDestroy{
title = 'Banking App';
 username: string | null = '';
accounts: Account[] = [];
userdetails: Account = new Account();
 timeRemaining: string = '00:00';
  private timer: any;
constructor(private authService: AuthService,private router:Router,private accountService:AccountService) {}

 ngOnInit(): void {
    this.username = this.authService.getUsername();
   this.accountService.getAllAccounts().subscribe(data => {
      this.accounts = data;
       this.userdetails=this.accounts[0];
   

  });
 
    
  this.startTimer();
 
  }
    ngOnDestroy(): void {
    if (this.timer) {
      clearInterval(this.timer);
    }
  }


logout(){
  alert("Your Session is Ended!");
    this.authService.logout(); 
    
              this.router.navigate(['/login']); // Redirect to LoginComponent
          
  }


  showDetails: boolean = false;

Details(): void {
  this.showDetails = !this.showDetails;
}
navigatechangepassword():void{
  this.router.navigate(['/Home/change-password']);
}


   private startTimer(): void {
    const token = this.authService.getToken();
    if (!token) {
      this.logout();
      return;
    }

    const decoded: any = jwtDecode(token);
    const expiresAt = decoded.exp * 1000;
    const timeout = expiresAt - Date.now();

    if (timeout <= 0) {
      this.logout();
      return;
    }

    this.timer = setInterval(() => {
      const remainingTime = expiresAt - Date.now();
      if (remainingTime <= 0) {
        this.timeRemaining = '00:00';
        this.logout();
      } else {
        const minutes = Math.floor(remainingTime / 60000);
        const seconds = Math.floor((remainingTime % 60000) / 1000);
        this.timeRemaining = `${this.pad(minutes)}:${this.pad(seconds)}`;
      }
    }, 1000);
  }
  private pad(value: number): string {
    return value < 10 ? `0${value}` : value.toString();
  }


}
