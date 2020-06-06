import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student-management/student-service/student.service';
import { ClassService } from '../class-management/class-service/class.service';
import { PaymentService } from './payment-service/payment.service';
import { Class } from '../class-management/class-service/class';

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

  constructor(private studentService: StudentService, private classService: ClassService, private paymentservice: PaymentService) { }

  ngOnInit(): void {
    this.aditionalLabel1 = 'Class Code';
    this.aditionalLabel2 = 'Class Name';
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
}
