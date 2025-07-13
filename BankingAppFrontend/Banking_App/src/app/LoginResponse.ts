import { Account } from "./account";

export interface LoginResponse {
  token?: string;
  otpRequired: boolean;
  account: Account | null;
}