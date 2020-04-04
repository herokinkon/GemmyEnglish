import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Observable } from 'rxjs';
import { Classes } from './classes-model';

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

  getClasses(): Observable<Classes[]> {
    return this.http.get<Classes[]>(this.apiUrl, this.httpOptions);
  }

  getClassesById(id: number): Observable<Classes> {
    return this.http.get<Classes>(this.apiUrl + `${id}`, this.httpOptions);
  }

  deleteClass(id: number): Observable<Classes> {
    return this.http.delete<Classes>(this.apiUrl + `${id}`, this.httpOptions);
  }

  createClass(classes: Classes): Observable<Classes> {
    return this.http.post<Classes>(this.apiUrl, classes);
  }

  updateClass(classes: Classes): Observable<Classes> {
    return this.http.put<Classes>(this.apiUrl, classes, this.httpOptions);
  }
}
