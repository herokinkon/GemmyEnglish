import { Component, OnInit } from '@angular/core';
import { StaffService } from '../staff-management/staff-service/staff.service';
import { Staff } from '../staff-management/staff-service/staff';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  fields: any[];
  userInfo: Staff;
  otherInfo: any[];

  constructor(private staffService: StaffService) {
    this.staffService.getStaff(1).subscribe(staff => this.userInfo = staff);
  }

  ngOnInit(): void {
    this.fields = [{ field: 'fullName', header: 'Full Name', required: 'true' },
    { field: 'birthday', header: 'Birthday' },
    { field: 'email', header: 'Email' },
    { field: 'contactNumber', header: 'Phone Number' },
    { field: 'facebook', header: 'Facebook' },
    { field: 'salary', header: 'Salary' }];

    // Other tab
    this.otherInfo = [
      { field: 'bankAccount', header: 'Bank Account' },
      { field: 'bankName', header: 'Bank Name' },
      { field: 'bankBranch', header: 'Bank Branch' },
      { field: 'ieltsScore', header: 'Ielts Score' },
      { field: 'othersCertificate', header: 'Other Certificate' }];
  }
}
