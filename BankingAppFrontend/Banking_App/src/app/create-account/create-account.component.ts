 import { Component } from '@angular/core';
import { AccountService } from '../account.service';
import { Account } from '../account';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../login.service';

@Component({
  selector: 'app-create-account',
  standalone: false,
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.css'
})
export class CreateAccountComponent {
username: string | null = '';
account:Account = new Account();
accountCreate=false;
  constructor(private accountService:AccountService,private authService:AuthService,private router:Router){}
  onSubmit(){
    this.saveAccount()
  }
  ngOnInit() {
    
   this.username= this.authService.getUsername();

  }
saveAccount(){
  this.accountService.createAccount(this.account).subscribe(data=>{
  console.log(data);
  this.accountCreate=true;
  setTimeout(()=>{
  this.goToAccountList();
  },1000);
})

}
goToAccountList(){
this.router.navigate(['/Home/accounts'])
}
 logout() {
    this.authService.logout(); 
    
              this.router.navigate(['/login']); // Redirect to LoginComponent
          
  }
}