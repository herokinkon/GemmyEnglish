import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PaymentService } from '../payment-service/payment.service';

@Component({
  selector: 'app-new-paymet',
  templateUrl: './new-payment.component.html',
  styleUrls: ['./new-payment.component.css']
})
export class NewPaymentComponent {

  fee: number;
  title: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private paymentService: PaymentService) {
    data.payMonths = 1;
    data.kindOfPayment = data.kindOfPayment ? data.kindOfPayment : 'Cash';
    data.month = data.month ? data.month : 1;
    data.discount = data.discount ? data.discount : 1;
    this.fee = data.amount;
    this.title = data.isNewPayment ? 'New Payment' : 'Edit Payment';
  }

  save(months: number, discount: number) {
    this.data.amount = this.data.fee * months * discount;
    this.data.month = months;
    if (this.data.isNewPayment) {
      this.paymentService.createNewPayment(this.data.studentId, this.data.classId, this.data).subscribe();
    } else {
      this.paymentService.updatePayment(this.data).subscribe();
    }
  }

}
