import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Staff } from './staff';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StaffService {


  apiUrl = AppConstant.serverURL + '/staff/';
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http: HttpClient) { }

  getStaffs(): Observable<Staff[]> {
    return this.http.get<Staff[]>(this.apiUrl, this.httpOptions);
  }

  getStaff(id: number): Observable<Staff> {
    return this.http.get<Staff>(this.apiUrl + `${id}`, this.httpOptions);
  }

  deleteStaff(id: number): Observable<Staff> {
    return this.http.delete<Staff>(this.apiUrl + `${id}`, this.httpOptions);
  }

  createStaff(staff: Staff): Observable<Staff> {
    return this.http.post<Staff>(this.apiUrl, staff);
  }

  updateStaff(staff: Staff): Observable<Staff> {
    return this.http.put<Staff>(this.apiUrl, staff, this.httpOptions);
  }

  searchStaffByName(searchText: string) {
    const params = new HttpParams({ fromObject: { searchText } });
    const data = { ...this.httpOptions, params: params };
    return this.http.get<Staff[]>(this.apiUrl + 'searchStaffByName', data)
  }

  searchStaff(searchText: string, type: string) {
    const data = { ...this.httpOptions, params: { searchText, type } };
    return this.http.get<Staff[]>(this.apiUrl + 'searchStaff', data);
  }
}
