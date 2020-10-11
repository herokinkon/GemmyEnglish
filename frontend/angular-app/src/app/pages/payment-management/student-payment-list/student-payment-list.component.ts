import { Component, Input, Output, EventEmitter } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { NewPaymentComponent } from '../new-payment/new-payment.component';

@Component({
  selector: 'app-student-payment-list',
  templateUrl: './student-payment-list.component.html',
  styleUrls: ['./student-payment-list.component.css']
})
export class StudentPaymentListComponent {

  cols: any;
  @Output() paymentChange: EventEmitter<any> = new EventEmitter();
  // tslint:disable-next-line:variable-name
  private _payments: any;
  private studentInfoCols = [{ field: 'fullName', header: 'Student Name' },
  { field: 'birthday', header: 'Birthday' }];
  private classInfoCols = [{ field: 'classCode', header: 'Class Code' },
  { field: 'className', header: 'Class Name' }];
  currentMonth: string;

  private classInfo: any;
  private studentInfo: any;

  isHidden = true;
  constructor(public dialog: MatDialog) {
    const date = new Date();
    this.currentMonth = date.getMonth() + 1 + '/' + date.getFullYear();
  }

  @Input()
  set payments(payments: any) {
    if (payments) {
      this.isHidden = false;
      this.clearAllState();

      if (payments.clazz) {
        this.classInfo = {
          classId: payments.clazz.id, classCode: payments.clazz.classCode,
          className: payments.clazz.className, fee: payments.clazz.fee,
          startDate: payments.clazz.startDate, endDate: payments.clazz.endDate
        };
        const months = this.getMonthsHeader(new Date(this.classInfo.startDate), new Date(this.classInfo.endDate));
        this.cols = this.studentInfoCols.concat(months);
        this._payments = payments.payment.map((pay: any) => this.fillPayment(pay, months));
      } else {
        this.studentInfo = { studentId: payments.student.id, fullName: payments.student.fullName, birthday: payments.student.birthday };
        if (payments.payment && payments.payment.startDate && payments.payment.endDate) {
          const months = this.getMonthsHeader(new Date(payments.payment.startDate), new Date(payments.payment.endDate));
          this.cols = this.classInfoCols.concat(months);
          this._payments = [this.fillPayment(payments.payment, months)];
        }
      }
    }
  }

  get payments() {
    return this._payments;
  }

  getMonthsHeader(startDate: Date, endDate: Date): any[] {
    const monthRange: { field: string, header: string, type: string }[] = [];
    while (startDate.getMonth() !== endDate.getMonth()) {
      const month = startDate.getMonth() + 1 + '/' + startDate.getFullYear();
      monthRange.push({ field: month, header: month, type: 'date' });
      startDate.setMonth(startDate.getMonth() + 1);
    }
    return monthRange;
  }

  fillPayment(payment: any, months: any): any {
    for (let i = 0; i < payment.months; i++) {
      if (months && months.length != 0 && months[i]) {
        payment[months[i].field] = 1;
      }
    }
    payment.canAdd = payment.months < months.length;
    payment.availableMonth = months.length - payment.months;
    return payment;
  }

  newPayment(rowData: any) {
    if (rowData.canAdd) {
      let dialogData: any;
      if (this.classInfo) {
        dialogData = { ...this.classInfo, ...rowData, isNewPayment: true };
      } else {
        dialogData = { ...this.studentInfo, ...rowData, isNewPayment: true };
      }
      const dialog = this.dialog.open(NewPaymentComponent, { data: dialogData });
      dialog.afterClosed().subscribe(() => this.paymentChange.emit());
    }
  }

  clearAllState(): void {
    this.cols = [];
    this._payments = [];
    this.classInfo = null;
    this.studentInfo = null;
  }
}
