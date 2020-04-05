import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Observable } from 'rxjs';
import { Class } from './class';

@Injectable({
    providedIn: 'root'
})
export class ClassService {

    apiUrl = AppConstant.serverURL + '/classes/';
    // Http Options
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };
    constructor(private http: HttpClient) { }

    getClassByStudent(studentId: number): Observable<Class[]> {
        const data = { ...this.httpOptions, params: { studentId: studentId.toString() } };
        return this.http.get<Class[]>(this.apiUrl + 'getClassByStudent', data);
    }
}
