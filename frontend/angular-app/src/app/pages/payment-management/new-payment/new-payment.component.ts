import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PaymentService } from '../payment-service/payment.service';

@Component({
  selector: 'app-new-paymet',
  templateUrl: './new-payment.component.html',
  styleUrls: ['./new-payment.component.css']
})
export class NewPaymentComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private paymentService: PaymentService) {
    data.payMonths = 1;
    data.kindOfPayment = 'Cash';
  }

  save(months: number, amount: number) {
    console.log(months + '' + amount);
    console.log(this.data);
    this.data.amount = amount;
    this.data.month = months;
    this.paymentService.createNewPayment(this.data.studentId, this.data.classId, this.data).subscribe();
  }

}
