import { Component } from '@angular/core';
import { AccountService } from '../account.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from '../account';
import { AuthService } from '../login.service';

@Component({
  selector: 'app-account-details',
  standalone: false,
  templateUrl: './account-details.component.html',
  styleUrl: './account-details.component.css'
})
export class AccountDetailsComponent {
  id:number=0;
 account:Account=new Account();
username: string | null = '';

constructor(private accountService:AccountService,private route:ActivatedRoute,private authService:AuthService,private router:Router){}

ngOnInit(){
this.id=this.route.snapshot.params['id'];
this.accountService.getAccountById(this.id).subscribe(data=>{
  this.account=data;
     this.username= this.authService.getUsername();
})

}
 logout() {
    this.authService.logout(); 
    
              this.router.navigate(['/login']); // Redirect to LoginComponent
          
  }
}
