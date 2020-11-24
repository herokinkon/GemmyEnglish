import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { Observable } from 'rxjs';
import { Payment } from './payment';
import { NotificationService } from 'src/app/shared/service/notification.service';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  apiUrl = AppConstant.serverURL + '/fee/';
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http: HttpClient, private notification: NotificationService) { }

  getPaymentInClass(classId: any): Observable<any[]> {
    const data = { ...this.httpOptions, params: { classId } };
    return this.http.get<Payment[]>(this.apiUrl + 'getAllPaymentInClass', data);
  }

  getPaymentForStudent(studentId: any): Observable<any[]> {
    const data = { ...this.httpOptions, params: { studentId } };
    return this.http.get<Payment[]>(this.apiUrl + 'getAllPaymentForStudent', data);
  }

  createNewPayment(studentId: number, classId: number, payment: Payment) {
    payment.studentInfo = { id: studentId };
    payment.classes = { id: classId };
    payment.date = new Date();
    return this.http.post(this.apiUrl, payment);
  }

  getAllPayment() {
    const data = { ...this.httpOptions };
    return this.http.get<any>(this.apiUrl + 'getAll', data).pipe(
      tap(_ => this.notification.addSuccessMessage('', 'Finished loading all payments')));
  }

  updatePayment(payment: Payment) {
    return this.http.put<Payment>(this.apiUrl, payment, this.httpOptions);
  }

  deletePayment(id: string) {
    return this.http.delete<Payment>(this.apiUrl + `${id}`, this.httpOptions);
  }

  getPayment(id: string) {
    return this.http.get<Payment>(this.apiUrl + `${id}`, this.httpOptions);
  }
}
