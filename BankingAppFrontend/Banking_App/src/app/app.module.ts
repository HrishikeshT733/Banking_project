import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccountListComponent } from './account-list/account-list.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { CreateAccountComponent } from './create-account/create-account.component';
import { FormsModule } from '@angular/forms';
import { DepositComponent } from './deposit/deposit.component';
import { WithdrawComponent } from './withdraw/withdraw.component';
import { AccountDetailsComponent } from './account-details/account-details.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { StatementComponent } from './statement/statement.component';
import { JwtInterceptor } from './JwtInterceptor';
import { ChangePasswordComponent } from './change-password/change-password.component';

@NgModule({
  declarations: [
    AppComponent,
    AccountListComponent,
    CreateAccountComponent,
    DepositComponent,
    WithdrawComponent,
    AccountDetailsComponent,
    LoginComponent,
    HomeComponent,
    StatementComponent,
    ChangePasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
