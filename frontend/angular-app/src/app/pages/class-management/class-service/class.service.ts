import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Observable } from 'rxjs';
import { Classes, Course } from './classes-model';
import { Class } from './class';
import { Student } from '../../student-management/student-service/student';

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
    return this.http.put<Classes>(this.apiUrl + 'attendance', { classInfo: classes, studentInfo: studentList} , this.httpOptions);
  }
}
