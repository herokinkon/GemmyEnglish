import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstant } from 'src/app/shared/app-constant.service';
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
    const data = { ...this.httpOptions, params: params };
    return this.http.get<Student[]>(this.apiUrl + 'searchStudent', data)
  }

  getNewStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl + 'getNewStudents', this.httpOptions);
  }

}
