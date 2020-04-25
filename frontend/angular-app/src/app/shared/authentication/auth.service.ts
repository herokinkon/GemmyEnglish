import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { AppConstant } from '../app-constant.service';
import { tap } from 'rxjs/operators';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }
  apiUrl = AppConstant.serverURL + '/login';

  login(username: string, password: string) {
    return this.http.post<any>(this.apiUrl, { username, password }, { observe: 'response' }).pipe(tap(this.storeToken));
  }

  isFeatureOn(feature: string): boolean {
    const userStr = localStorage.getItem(AppConstant.STORED_TOKEN);
    if (userStr) {
      const user: User = JSON.parse(userStr);
      return user.features[feature];
    }
    return false;
  }

  private storeToken(res: HttpResponse<any>) {
    const token = res.headers.get(AppConstant.HEADER_TOKEN);
    localStorage.setItem(AppConstant.STORED_TOKEN, token);
  }

  isAuthenticated(): boolean {
    return localStorage.getItem(AppConstant.STORED_TOKEN) != null;
  }

  logout() {
    localStorage.removeItem(AppConstant.STORED_TOKEN);
  }
}
