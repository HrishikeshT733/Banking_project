import { Component } from '@angular/core';
import { AccountService } from '../account.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from '../account';
import { AuthService } from '../login.service';

@Component({
  selector: 'app-withdraw',
  standalone: false,
  templateUrl: './withdraw.component.html',
  styleUrl: './withdraw.component.css'
})
export class WithdrawComponent {
  id: number = 0;
  account: Account = new Account();
  withdrawAmount: number = 0;
 
 username: string | null = '';
  successMessage = '';
  errorMessage = '';

  constructor(
    private accountService: AccountService,
    private route: ActivatedRoute,
  private authService:AuthService,private router:Router
  ) {}

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.accountService.getAccountById(this.id).subscribe(data => {
      this.account = data;
      this.authService.getUsername();
    });
  }

  onSubmit() {
    if (this.withdrawAmount <= 0) {
      this.errorMessage = "Amount must be greater than 0";
    } else if (this.withdrawAmount > this.account.balance) {
      this.errorMessage = "Insufficient balance!";
    } else if (this.withdrawAmount > 1000000) {
      this.errorMessage = "Amount must be less than 10 lakh";
    } else {
      this.accountService.Withdraw(this.id, this.withdrawAmount).subscribe(data => {
        this.account = data;
        this.successMessage = "Withdrawn successfully!";
        setTimeout(() => this.router.navigate(['/Home/accounts']), 1000);
      });
      return; // prevent clearing error message prematurely
    }

    // Clear error after timeout
    setTimeout(() => {
      this.errorMessage = "";
    }, 2000);
  }

  logout() {
    this.authService.logout(); 
    
              this.router.navigate(['/login']); // Redirect to LoginComponent
          
  }
}
