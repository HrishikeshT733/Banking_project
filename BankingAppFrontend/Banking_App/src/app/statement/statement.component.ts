import { Component } from '@angular/core';
import { Transaction } from '../Transaction';
import { AccountService } from '../account.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../login.service';
@Component({
  selector: 'app-statement',
  standalone: false,
  templateUrl: './statement.component.html',
  styleUrl: './statement.component.css'
})
export class StatementComponent {
  id:number=0;
 transactions:Transaction[]=[];
 filteredTransactions: Transaction[] = [];
 fromDate: string = '';
  toDate: string = '';
username: string | null = '';
  constructor(private accountService:AccountService,private route:ActivatedRoute,private authService:AuthService,private router:Router){}

ngOnInit(){
  this.id=this.route.snapshot.params['id']
this.getTransactions();
this.authService.getUsername();

}

getTransactions(){
  this.accountService.getStatementById(this.id).subscribe(data =>{
    
    this.transactions=data;
    this.filteredTransactions =[]; // initially show all
  })
}
filterTransactions() {
    if (!this.fromDate || !this.toDate) {
      this.filteredTransactions = this.transactions; // no filter
      return;
    }

    const from = new Date(this.fromDate);
    const to = new Date(this.toDate);

    // Ensure 'to' date includes entire day
    to.setHours(23, 59, 59, 999);

    this.filteredTransactions = this.transactions.filter(tx => {
      const txDate = new Date(tx.timestamp);
      return txDate >= from && txDate <= to;
    });
  }


 logout() {
    this.authService.logout(); 
    
              this.router.navigate(['/login']); // Redirect to LoginComponent
          
  }
}

// import { Component } from '@angular/core';
// import { Transaction } from '../Transaction';
// import { AccountService } from '../account.service';
// import { ActivatedRoute, Router } from '@angular/router';
// import { AuthService } from '../login.service';

// @Component({
//   selector: 'app-statement',
//   templateUrl: './statement.component.html',
//   styleUrl: './statement.component.css'
// })
// export class StatementComponent {
//   id: number = 0;
//   transactions: Transaction[] = [];
//   filteredTransactions: Transaction[] = [];

//   fromDate: string = '';
//   toDate: string = '';

//   username: string | null = '';

//   constructor(
//     private accountService: AccountService,
//     private route: ActivatedRoute,
//     private authService: AuthService,
//     private router: Router
//   ) {}

//   ngOnInit() {
//     this.id = this.route.snapshot.params['id'];
//     this.getTransactions();
//     this.username = this.authService.getUsername();
//   }

//   getTransactions() {
//     this.accountService.getStatementById(this.id).subscribe(data => {
//       this.transactions = data;
//       this.filteredTransactions = data; // initially show all
//     });
//   }

//   filterTransactions() {
//     if (!this.fromDate || !this.toDate) {
//       this.filteredTransactions = this.transactions; // no filter
//       return;
//     }

//     const from = new Date(this.fromDate);
//     const to = new Date(this.toDate);

//     // Ensure 'to' date includes entire day
//     to.setHours(23, 59, 59, 999);

//     this.filteredTransactions = this.transactions.filter(tx => {
//       const txDate = new Date(tx.timestamp);
//       return txDate >= from && txDate <= to;
//     });
//   }

//   logout() {
//     this.authService.logout();
//     this.router.navigate(['/login']);
//   }
// }
