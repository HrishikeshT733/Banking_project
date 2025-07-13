import { Role } from "./Role";
export class Account {
    id: number=0;
    accountHolderName: string="";
    balance: number=0;
    email:string="";
    password:string="";
    phoneNumber:string="";
 role:Role|null=null;
  }