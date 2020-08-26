import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student-management/student-service/student.service';
import { ClassService } from '../class-management/class-service/class.service';
import { PaymentService } from './payment-service/payment.service';
import { Payment } from './payment-service/payment';
import { LazyLoadEvent } from 'primeng/api/primeng-api';
import { NewPaymentComponent } from './new-payment/new-payment.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-payment-management',
  templateUrl: './payment-management.component.html',
  styleUrls: ['./payment-management.component.css']
})
export class PaymentManagementComponent implements OnInit {
  selectedFilter: string;
  results: any;
  aditionalLabel1: string;
  aditionalField1: string;
  aditionalLabel2: string;
  aditionalField2: string;

  payments: any;

  paymentList: any;
  cols: any;
  totalRecords: number;
  loading: boolean;

  constructor(private studentService: StudentService, private classService: ClassService, private paymentservice: PaymentService,
              public dialog: MatDialog) {
    this.cols = [
    { field: 'date', header: 'Date' },
    { field: 'full_name', header: 'Student Name' },
    { field: 'class_name', header: 'Class Name' },
    { field: 'kind_of_payment', header: 'Kind of Payment' },
    { field: 'month', header: 'Months' },
    { field: 'amount', header: 'Amount' }];

  }

  ngOnInit(): void {
    this.aditionalLabel1 = 'Class Code';
    this.aditionalLabel2 = 'Class Name';

    this.paymentservice.getAllPayment().subscribe(pay => this.paymentList = pay);
  }

  search($event: any, selectedOption: any) {
    if (selectedOption.label === 'Class') {
      this.classService.getClassesListByName($event.query, { page: 0, size: 10 })
        .subscribe(classes => this.results = classes);
    } else {
      this.studentService.getStudentListByName($event.query, { page: 0, size: 10 })
        .subscribe(student => this.results = student);
    }
  }

  changeSearchCriteria(value: string) {
    this.aditionalField1 = '';
    this.aditionalField2 = '';
    this.selectedFilter = '';
    if (value === 'Class') {
      this.aditionalLabel1 = 'Class Code';
      this.aditionalLabel2 = 'Class Name';
    } else {
      this.aditionalLabel1 = 'Student Name';
      this.aditionalLabel2 = 'Birthday';
    }
  }

  fillSearchResults($event: any, selectedOption: any) {
    if (selectedOption === 'Class') {
      this.aditionalField1 = $event.classCode;
      this.aditionalField2 = $event.className;
      this.paymentservice.getPaymentInClass($event.id).subscribe(
        payment => this.payments = { payment, clazz: $event });
    } else {
      this.aditionalField1 = $event.fullName;
      this.aditionalField2 = $event.birthday;
      this.paymentservice.getPaymentForStudent($event.id).subscribe(
        payment => this.payments = { payment, student: $event });
    }
  }

  onRowSelect($event) {
    this.paymentservice.getPayment($event.data.id).subscribe(val => {
      const dialogData = {
        ...val, fullName: val.studentInfo.fullName, birthday: val.studentInfo.birthday,
        classCode: val.classes.classCode, className: val.classes.className,
        fee: val.classes.fee, isNewPayment: false,
        discount: Math.round(val.amount / (val.classes.fee * val.month) * 100) / 100
      };
      const dialog = this.dialog.open(NewPaymentComponent, { data: dialogData });
      dialog.afterClosed().subscribe(() => this.paymentservice.getAllPayment().subscribe(pay => this.paymentList = pay));
    });
  }
}
