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
 username: string | null = '';
  constructor(private accountService: AccountService, private authService:AuthService,private router:Router) { }

  ngOnInit() {
    this.getAccounts();
   this.username= this.authService.getUsername();

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

  getAccounts() {
    this.accountService.getAllAccounts().subscribe(data => {
      this.accounts = data;

    })
  }
 
  deposit(id: number) {
    this.router.navigate(['/Home/deposit', id])
  }

  withdraw(id: number) {
    this.router.navigate(['/Home/withdraw', id])
  }

  delete(id: number) {
    this.accountService.delete(id).subscribe(data => {
      
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
