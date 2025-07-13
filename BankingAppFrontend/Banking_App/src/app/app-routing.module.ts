import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountListComponent } from './account-list/account-list.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { DepositComponent } from './deposit/deposit.component';
import { WithdrawComponent } from './withdraw/withdraw.component';
import { AccountDetailsComponent } from './account-details/account-details.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { StatementComponent } from './statement/statement.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
const routes: Routes = [
  // {path:'Home/accounts',component:AccountListComponent},
  // {path:'Home/create-account',component:CreateAccountComponent},
  // {path:'deposit/:id',component:DepositComponent},
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  // {path:'withdraw/:id',component:WithdrawComponent},
  // {path:'account-details/:id',component:AccountDetailsComponent},
  { path: 'login', component: LoginComponent },
  {
    path: 'Home',
    component: HomeComponent,
    children: [
      { path: 'accounts', component: AccountListComponent },
      { path: 'create-account', component: CreateAccountComponent },
      { path: 'deposit/:id', component: DepositComponent },
      { path: 'withdraw/:id', component: WithdrawComponent },
      { path: 'account-details/:id', component: AccountDetailsComponent },
      { path: 'statement/:id', component: StatementComponent },
      { path: 'change-password', component: ChangePasswordComponent },
      { path: '', redirectTo: 'Home', pathMatch: 'full' } // Redirect to parent
    ]
  },

  // {path:'statement/:id',component:StatementComponent},
  // { path: 'change-password', component: ChangePasswordComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
