import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Staff } from '../../staff-management/staff-service/staff';
import { OtherOutcome } from './salary';
import { NotificationService } from 'src/app/shared/service/notification.service';
import { tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class SalaryService {

    apiUrl = AppConstant.serverURL + '/salary/';
    // Http Options
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };
    constructor(private http: HttpClient, private notification: NotificationService) { }

    getFullTimeStaffSalary(): Observable<Staff[]> {
        return this.http.get<Staff[]>(this.apiUrl + 'fulltime', this.httpOptions).pipe(
            tap(_ => this.notification.addSuccessMessage('', 'Finished loading all salaries')));
    }

    getPartTimeStaffSalary(): Observable<Staff[]> {
        return this.http.get<Staff[]>(this.apiUrl + 'parttime', this.httpOptions);
    }

    getOtherOutcome(): Observable<OtherOutcome[]> {
        return this.http.get<OtherOutcome[]>(this.apiUrl + 'othersoutcome', this.httpOptions);
    }

    getOtherOutcomeById(id: number): Observable<OtherOutcome> {
        return this.http.get<OtherOutcome>(this.apiUrl + `${id}`, this.httpOptions);
    }

    // getStaffSalary(id: number): Observable<Salary> {
    //     return this.http.get<Salary>(this.apiUrl + `${id}`, this.httpOptions);
    // }

    deleteOtherOutcome(id: number): Observable<OtherOutcome> {
        return this.http.delete<OtherOutcome>(this.apiUrl + `${id}`, this.httpOptions);
    }

    createOtherOutcome(otherOutcome: OtherOutcome): Observable<OtherOutcome> {
        return this.http.post<OtherOutcome>(this.apiUrl, otherOutcome);
    }

    updateOtherOutcome(otherOutcome: OtherOutcome): Observable<OtherOutcome> {
        return this.http.put<OtherOutcome>(this.apiUrl, otherOutcome, this.httpOptions);
    }

}
