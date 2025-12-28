import { Component } from '@angular/core';
import { AccountService } from '../account.service';
import { Account } from '../account';
import { Router } from '@angular/router';
 import { AuthService } from '../login.service';
 import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-account-list',
  standalone: false,
  templateUrl: './account-list.component.html',
  styleUrl: './account-list.component.css'
})
export class AccountListComponent {

  accounts: Account[] = [];
  currentUser:Account=new Account();
  tempAccounts:Account[]=[];
 username: string | null = '';
  constructor(private accountService: AccountService, private authService:AuthService,private router:Router) { }

  ngOnInit() {
    this.getAccounts();
   this.username= this.authService.getUsername();
//on ngOnInit foreach gets triggered first thats why i have added foreach() logic in method getaccounts()

  }
visibleBalances = new Set<number>();

toggleBalance(accountId: number) {
  if (this.visibleBalances.has(accountId)) {
    this.visibleBalances.delete(accountId);
  } else {
    this.visibleBalances.add(accountId);
  }
}

isBalanceVisible(accountId: number): boolean {
  return this.visibleBalances.has(accountId);
}
getaccountlist(){
 this.accounts.forEach((accountTemp)=>{
if(accountTemp.email===this.username){
  this.currentUser=accountTemp;
}
   });
   if(this.currentUser.role==='USER'){
  this.accounts.forEach((accountTemp)=>{
    if(accountTemp.id===this.currentUser.id && accountTemp.role!=='ADMIN'){
    this.tempAccounts.push(accountTemp);
    }
  });
}else if(this.currentUser.role==='ADMIN'){
     this.accounts.forEach((accountTemp)=>{
    if(accountTemp.id!==this.currentUser.id){
    this.tempAccounts.push(accountTemp);
    }
  });
}

}
  getAccounts() {
    this.accountService.getAllAccounts().subscribe(data => {
      this.accounts = data;
      this.getaccountlist();
    });
   
  }
 
  deposit(id: number) {
    this.router.navigate(['/Home/deposit', id])
  }

  withdraw(id: number) {
    this.router.navigate(['/Home/withdraw', id])
  }
   transfer(id: number) {
    this.router.navigate(['/Home/transfer', id])
  }

  delete(id: number) {
    this.accountService.delete(id).subscribe(data => {
      window.location.reload();
      this.getAccounts();
   
       
    })
  }

  view(id: number) {
    this.router.navigate(['/Home/account-details', id])

  }
  statement(id: number) {
    this.router.navigate(['/Home/statement', id])
  }

  
}
