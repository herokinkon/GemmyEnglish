import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Observable } from 'rxjs';
import { UserAccount } from './user-account';
import { NotificationService } from 'src/app/shared/service/notification.service';
import { tap } from 'rxjs/operators';

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

  constructor(private http: HttpClient, private notification: NotificationService) { }

  getAccount(id: number): Observable<UserAccount> {
    return this.http.get<UserAccount>(this.apiUrl + `/${id}`, this.httpOptions);
  }

  createAccount(account: UserAccount): Observable<UserAccount> {
    return this.http.post<UserAccount>(this.apiUrl, account).pipe(
      tap(_ => this.notification.addSuccessMessage('Account', 'New Account is created')));
  }

  deleteAccount(account: UserAccount): Observable<UserAccount> {
    return this.http.delete<UserAccount>(this.apiUrl + `/${account.id}`, this.httpOptions).pipe(
      tap(_ => this.notification.addSuccessMessage('Account', `[${account.userName}] is deleted`)));
  }

  getAccounts(): Observable<UserAccount[]> {
    return this.http.get<UserAccount[]>(this.apiUrl, this.httpOptions).pipe(
      tap(_ => this.notification.addSuccessMessage('', 'Finished loading all accounts')));
  }

  updateAccount(account: UserAccount): Observable<UserAccount> {
    return this.http.put<UserAccount>(this.apiUrl, account).pipe(
      tap(_ => this.notification.addSuccessMessage('Account', `[${account.userName}] is updated`)));
  }
}
