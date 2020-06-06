import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Student } from '../../student-management/student-service/student';
import { Class } from './class';
import { Classes, Course } from './classes-model';

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

  getClassByStudent(studentId: number): Observable<Class[]> {
    const data = { ...this.httpOptions, params: { studentId: studentId.toString() } };
    return this.http.get<Class[]>(this.apiUrl + 'getClassByStudent', data);
  }

  getCourse(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl + 'getCourse', this.httpOptions);
  }

  updateClassAndAttendance(classes: Classes, studentList: Student[]): Observable<Classes> {
    return this.http.put<Classes>(this.apiUrl + 'classAttendance', { classInfo: classes, studentInfo: studentList }, this.httpOptions);
  }

  updateAttedance(classes: Classes, studentList: Student[]): Observable<Classes> {
    return this.http.put<Classes>(this.apiUrl + 'attendance', { classInfo: classes, studentInfo: studentList }, this.httpOptions);
  }

  searchClass(searchText: string) {
    const params = new HttpParams({ fromObject: { searchText } });
    const data = { ...this.httpOptions, params: params };
    return this.http.get<Classes[]>(this.apiUrl + 'searchClass', data)
  }

  getClassesListByName(name: string, { page, size }): Observable<Classes[]> {
    const data = { ...this.httpOptions, params: { name, page, size } };
    return this.http.get<Classes[]>(this.apiUrl + 'getClassesListByName', data);
  }

}
