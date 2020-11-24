import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Exam, ExamResult } from './exam.model';
import { NotificationService } from 'src/app/shared/service/notification.service';
import { tap } from 'rxjs/operators';

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
    constructor(private http: HttpClient, private notification: NotificationService) { }

    getExams(): Observable<Exam[]> {
        return this.http.get<Exam[]>(this.apiUrl, this.httpOptions).pipe(
            tap(_ => this.notification.addSuccessMessage('', 'Finished loading all exams')));
    }

    getExamById(id: number): Observable<Exam> {
        return this.http.get<Exam>(this.apiUrl + `${id}`, this.httpOptions);
    }

    createExam(exam: Exam): Observable<Exam> {
        return this.http.post<Exam>(this.apiUrl, exam).pipe(
            tap(_ => this.notification.addSuccessMessage('Exam', 'New Exam is created')));
    }

    updateExam(exam: Exam) {
        return this.http.put<Exam>(this.apiUrl, exam, this.httpOptions).pipe(
            tap(_ => this.notification.addSuccessMessage('Exam', `[${exam.name}] is updated`)));
    }

    deleteExam(exam: Exam) {
        return this.http.delete<Exam>(this.apiUrl + `${exam.id}`, this.httpOptions).pipe(
            tap(_ => this.notification.addSuccessMessage('Exam', `[${exam.name}] is deleted`)));
    }

    searchExam(searchText: string) {
        const params = new HttpParams({ fromObject: { searchText } });
        const data = { ...this.httpOptions, params };
        return this.http.get<Exam[]>(this.apiUrl + 'searchExam', data)
    }

    searchResult(classId: string, studentId: string, examId: string) {
        const params = new HttpParams({ fromObject: { classId: classId, studentId: studentId, examId: examId } });
        const data = { ...this.httpOptions, params: params };
        return this.http.get<ExamResult[]>(this.apiUrl + 'searchResult', data)
    }

    getListStudentResult(classId: string, studentId: string, examId: string): Observable<ExamResult[]> {
        const params = new HttpParams({ fromObject: { classId: classId, studentId: studentId, examId: examId } });
        const data = { ...this.httpOptions, params: params };
        return this.http.get<ExamResult[]>(this.apiUrl + 'getListResult', data)
    }

    updateExamResult(examResult: ExamResult[]) {
        return this.http.put<ExamResult[]>(this.apiUrl + 'updateResult', examResult, this.httpOptions);
    }

}
