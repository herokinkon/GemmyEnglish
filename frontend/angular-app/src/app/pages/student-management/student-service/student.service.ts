import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Student } from './student';
import { tap } from 'rxjs/operators';
import { NotificationService } from 'src/app/shared/service/notification.service';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  apiUrl = AppConstant.serverURL + '/student/';
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http: HttpClient, private notification: NotificationService) { }

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl, this.httpOptions).pipe(
      tap(_ => this.notification.addSuccessMessage('', 'Finished loading all Students')));
  }

  getStudent(id: number): Observable<Student> {
    return this.http.get<Student>(this.apiUrl + `${id}`, this.httpOptions);
  }

  deleteStudent(id: number, name: string): Observable<Student> {
    return this.http.delete<Student>(this.apiUrl + `${id}`, this.httpOptions).pipe(
      tap(_ => this.notification.addSuccessMessage('Student', `[${name}] is deleted`)));
  }

  createStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.apiUrl, student).pipe(
      tap(_ => this.notification.addSuccessMessage('Student', 'New Student is created')));
  }

  updateStudent(student: Student): Observable<Student> {
    return this.http.put<Student>(this.apiUrl, student, this.httpOptions).pipe(
      tap(_ => this.notification.addSuccessMessage('Student', `[${student.fullName}] is updated`)));
  }

  getStudentListByClass(classId: any): Observable<Student[]> {
    const data = { ...this.httpOptions, params: { classId } };
    return this.http.get<Student[]>(this.apiUrl + 'getStudentListByClass', data);
  }

  getStudentListByName(name: string, { page, size }): Observable<Student[]> {
    const data = { ...this.httpOptions, params: { name, page, size } };
    return this.http.get<Student[]>(this.apiUrl + 'getStudentListByName', data);
  }

  searchStudent(searchText: string) {
    const params = new HttpParams({ fromObject: { searchText } });
    const data = { ...this.httpOptions, params };
    return this.http.get<Student[]>(this.apiUrl + 'searchStudent', data)
  }

  getNewStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl + 'getNewStudents', this.httpOptions);
  }

}
