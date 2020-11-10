import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Role } from '../account-service/user-account';
import { Staff } from '../../staff-management/staff-service/staff';
import { StaffService } from '../../staff-management/staff-service/staff.service';
import { AccountServices } from '../account-service/account.service';

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrls: ['./account-detail.component.css']
})
export class AccountDetailComponent {
  title: string;
  roles: string[] = ['Salesperson', 'Assistant', 'Teacher', 'Office', 'Academic', 'Admin'];
  password: string;
  staffSuggestion: Staff[];
  selectedStaff: Staff;
  isResetPwd: boolean;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private staffService: StaffService, private acccountService: AccountServices) {
    this.staffSuggestion = [data.staff];
    if (this.data.isNew) {
      this.data.status = true;
    }
  }

  save() {
    if (this.data.isNew) {
      this.acccountService.createAccount(this.data).subscribe();
    } else {
      if (this.isResetPwd) {
        this.data.password = this.password;
      }
      this.acccountService.updateAccount(this.data).subscribe();
    }
  }

  delete() {
    this.acccountService.deleteAccount(this.data).subscribe();
  }

  searchStaff(event: any) {
    this.staffService.searchStaffByName(event.query).subscribe(staffs => this.staffSuggestion = staffs);
  }
}
