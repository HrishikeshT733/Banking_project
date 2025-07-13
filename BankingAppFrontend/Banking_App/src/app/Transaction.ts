export class Transaction {
    id: number=0;
    type:string=""; // "DEPOSIT" or "WITHDRAWAL"
    amount:number=0;
    timestamp: Date = new Date(); 
    accountId:number=0;
  }