import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Observable } from 'rxjs';
import { Student } from './student';

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
  constructor(private http: HttpClient) { }

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl, this.httpOptions);
  }

  getStudent(id: number): Observable<Student> {
    return this.http.get<Student>(this.apiUrl + `${id}`, this.httpOptions);
  }

  deleteStudent(id: number): Observable<Student> {
    return this.http.delete<Student>(this.apiUrl + `${id}`, this.httpOptions);
  }

  createStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.apiUrl, student);
  }

  updateStudent(student: Student): Observable<Student> {
    return this.http.put<Student>(this.apiUrl, student, this.httpOptions);
  }

  getStudentListByClass(classId: Number): Observable<Student[]> {
    const data = { ...this.httpOptions, params: { classId: classId.toString() } };
    return this.http.get<Student[]>(this.apiUrl + 'getStudentListByClass', data);
  }

}
