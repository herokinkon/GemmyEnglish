import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Observable } from 'rxjs';
import { UserAccount } from './user-account';

@Injectable({
  providedIn: 'root'
})
export class AccountServices {
  apiUrl = AppConstant.serverURL + '/account';
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getAccount(id: number): Observable<UserAccount> {
    return this.http.get<UserAccount>(this.apiUrl + `/${id}`, this.httpOptions);
  }

  createAccount(account: UserAccount): Observable<UserAccount> {
    return this.http.post<UserAccount>(this.apiUrl, account);
  }

  deleteAccount(id: number): Observable<UserAccount> {
    return this.http.delete<UserAccount>(this.apiUrl + `/${id}`, this.httpOptions);
  }

  getAccounts(): Observable<UserAccount[]> {
    return this.http.get<UserAccount[]>(this.apiUrl, this.httpOptions);
  }

  updateAccount(account: UserAccount): Observable<UserAccount> {
    return this.http.put<UserAccount>(this.apiUrl, account);
  }
}
