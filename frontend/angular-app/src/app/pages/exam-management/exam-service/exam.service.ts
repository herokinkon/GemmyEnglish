import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Exam } from './exam.model';

@Injectable({
    providedIn: 'root'
})
export class ExamService {

    apiUrl = AppConstant.serverURL + '/exams/';
    // Http Options
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };
    constructor(private http: HttpClient) { }

    getExams(): Observable<Exam[]> {
        return this.http.get<Exam[]>(this.apiUrl, this.httpOptions);
    }

    getExamById(id: number): Observable<Exam> {
        return this.http.get<Exam>(this.apiUrl + `${id}`, this.httpOptions);
    }

    getExam(id: number): Observable<Exam> {
        return this.http.get<Exam>(this.apiUrl + `${id}`, this.httpOptions);
    }

    createExam(exam: Exam) {
        return null;
    }

    updateExam(exam: Exam) {
        return null;
    }

    deleteExam(examId: number) {
        return null;
    }
}
