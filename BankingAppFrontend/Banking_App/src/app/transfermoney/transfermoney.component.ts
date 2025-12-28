import { Component } from '@angular/core';
import { Account } from '../account';
import { AccountService } from '../account.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../login.service';
@Component({
  selector: 'app-transfermoney',
  standalone: false,
  templateUrl: './transfermoney.component.html',
  styleUrl: './transfermoney.component.css'
})
export class TransfermoneyComponent {
id:number=0;
account:Account=new Account();
amount:number=0;
toId:number=0;
username: string | null = '';
constructor(private accountService:AccountService,private rout:ActivatedRoute,private authService:AuthService,private router:Router){}

ngOnInit(){
  this.id=this.rout.snapshot.params['id'];
  this.accountService.getAccountById(this.id).subscribe(data=>{
 this.account=data;
 this.authService.getUsername();

  })
}

successMessage="";
errorMessage="";
onSubmit(){
  if(this.isValidAmount(this.amount)){
this.accountService.transferAmount(this.id,this.amount,this.toId).subscribe(data=>{
  this.account=data;
  this.successMessage="Amount Transfer Successfully....!";
setTimeout(()=>{
  this.router.navigate(['/Home/accounts'])

},1000);

  
 
   })
  }else{
  
this.errorMessage="Invalid Amount...please enter valid Amount...";

setTimeout(() => {
  this.errorMessage="";
}, 1000);

  }

}
isValidAmount(amount:number):boolean{
  return amount>0 && amount<10000000000
}
 logout() {
    this.authService.logout(); 
    
              this.router.navigate(['/login']); // Redirect to LoginComponent
          
  }
}
