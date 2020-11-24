import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Staff } from './staff';
import { Observable } from 'rxjs';
import { NotificationService } from 'src/app/shared/service/notification.service';
import { tap } from 'rxjs/operators';

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
  constructor(private http: HttpClient, private notification: NotificationService) { }

  getStaffs(): Observable<Staff[]> {
    return this.http.get<Staff[]>(this.apiUrl, this.httpOptions).pipe(
      tap(_ => this.notification.addSuccessMessage('', 'Finished loading all staffs')));
  }

  getStaff(id: number): Observable<Staff> {
    return this.http.get<Staff>(this.apiUrl + `${id}`, this.httpOptions);
  }

  deleteStaff(staff: Staff): Observable<Staff> {
    return this.http.delete<Staff>(this.apiUrl + `${staff.id}`, this.httpOptions).pipe(
      tap(_ => this.notification.addSuccessMessage('staff', `[${staff.fullName}] is deleted`)));
  }

  createStaff(staff: Staff): Observable<Staff> {
    return this.http.post<Staff>(this.apiUrl, staff).pipe(
      tap(_ => this.notification.addSuccessMessage('Staff', 'New Staff is created')));
  }

  updateStaff(staff: Staff): Observable<Staff> {
    return this.http.put<Staff>(this.apiUrl, staff, this.httpOptions).pipe(
      tap(_ => this.notification.addSuccessMessage('Account', `[${staff.fullName}] is updated`)));
  }

  searchStaffByName(searchText: string) {
    const params = new HttpParams({ fromObject: { searchText } });
    const data = { ...this.httpOptions, params };
    return this.http.get<Staff[]>(this.apiUrl + 'searchStaffByName', data);
  }

  searchStaff(searchText: string, type: string) {
    const data = { ...this.httpOptions, params: { searchText, type } };
    return this.http.get<Staff[]>(this.apiUrl + 'searchStaff', data);
  }

  getStaffListByClass(classId: any): Observable<Staff[]> {
    const data = { ...this.httpOptions, params: { classId } };
    return this.http.get<Staff[]>(this.apiUrl + 'getStaffListByClass', data);
  }

  getNewStaffs(): Observable<Staff[]> {
    return this.http.get<Staff[]>(this.apiUrl + 'getNewStaffs', this.httpOptions);
  }
}
