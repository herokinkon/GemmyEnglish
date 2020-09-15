import { Component, OnInit } from '@angular/core';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { CommonDialogService } from 'src/app/shared/components/common-detail-dialog/common-dialog.service';
import { EntityActionEvent } from 'src/app/shared/components/common-detail-dialog/common-entity-dialog-interface';
import { Staff, StaffInfo } from '../staff-management/staff-service/staff';
import { OtherOutcomeDetailComponent } from './other-outcome-detail/other-outcome-detail.component';
import { OtherOutcome, SalaryPartTimeResult } from './salary-service/salary';
import { SalaryService } from './salary-service/salary.service';
import { StaffService } from '../staff-management/staff-service/staff.service';

@Component({
  selector: 'salary-management',
  templateUrl: './salary-management.component.html',
  styleUrls: ['./salary-management.component.css']
})
export class SalaryManagementComponent implements OnInit {

  // tab 1
  salaryHeader: any[];
  salaryStaff: Staff[]
  filterSalary: any;

  // tab 2
  suggestionStaffs: Staff[];
  staffInfo: Staff;
  staff: Staff[];
  calculatePartTimeFields: any;
  selectedMonth: number;
  salaryPartTimeResult: SalaryPartTimeResult;

  // tab 3
  othersHeader: any[];
  othersOutcome: OtherOutcome[]
  selectedOutcome: any;

  constructor(private readonly dialog: CommonDialogService, private salaryService: SalaryService, private staffService: StaffService) {
    this.filterSalary = 'All';
    this.selectedMonth = 1;
  }

  ngOnInit(): void {
    this.salaryHeader = [{ field: 'fullName', header: 'Full Name' },
    { field: 'birthday', header: 'Birthday' },
    { field: 'contactNumber', header: 'Contact Number' },
    { field: 'salary', header: 'Salary (VND/Month)' }];

    this.calculatePartTimeFields = [{ field: 'fullName', header: 'Full Name' },
    { field: 'birthday', header: 'Birthday' },
    { field: 'contactNumber', header: 'Purpose' },
    { field: 'email', header: 'Date' },
    { field: 'bankName', header: 'Bank Name' },
    { field: 'bankAccount', header: 'Bank Account' },
    { field: 'bankBranch', header: 'Bank Branch' },
    { field: 'salary', header: 'Salary' }];

    this.othersHeader = [{ field: 'fullName', header: 'Full Name' },
    { field: 'birthday', header: 'Birthday' },
    { field: 'usedDate', header: 'Date' },
    { field: 'purpose', header: 'Purpose' },
    { field: 'cost', header: 'Cost' }];

    this.staffService.getStaffs().subscribe(data => this.salaryStaff = data);
    this.salaryService.getOtherOutcome().subscribe(data => this.othersOutcome = data);
    this.staffInfo = new StaffInfo();
  }

  ///////////// Other Outcome tab
  showDialogToAddOtherOutcome() {
    const result = this.dialog.openDialog('New Other Outcome', OtherOutcomeDetailComponent, {});
    result.subscribe(evt => this.updateOtherOutcomeTable(evt));
  }

  otherOutcomeSelect(event: any) {
    const result = this.dialog.openDialog('Other Outcome Detail', OtherOutcomeDetailComponent, { ...event.data });
    result.subscribe(evt => this.updateOtherOutcomeTable(evt));
  }

  updateOtherOutcomeTable(event: EntityActionEvent<OtherOutcome>) {
    switch (event?.action) {
      case ENTITY_ACTION.CREATE:
        if (!this.othersOutcome) {
          this.othersOutcome = [];
        }
        this.othersOutcome.push(event.entity);
        break;
      case ENTITY_ACTION.EDIT:
        const index = this.othersOutcome.findIndex(stu => event.entity.id === stu.id);
        if (index >= 0) {
          this.othersOutcome[index] = event.entity;
        }
        break;
      case ENTITY_ACTION.DELETE:
        let startIndex = this.othersOutcome.findIndex(stu => event.entity.id === stu.id)
        this.othersOutcome.splice(startIndex, 1);
        break;
    }
  }

  updateList() {
    console.log(this.filterSalary)
    if (this.filterSalary == "All") {
      this.staffService.getStaffs().subscribe(data => this.salaryStaff = data);
    }
    if (this.filterSalary == "Full Time") {
      this.salaryService.getFullTimeStaffSalary().subscribe(data => this.salaryStaff = data);
    }
    if (this.filterSalary == "Part Time") {
      this.salaryService.getPartTimeStaffSalary().subscribe(data => this.salaryStaff = data);
    }
  }

  // Calculate Part Time tab

  updateStaffInfoBox(event: any) {
    console.log(event)
    this.staffInfo = event;
  }

  searchStaff(event: any) {
    this.staffService.searchStaffByName(event.query).subscribe(data => {
      if (data) {
        this.suggestionStaffs = data;
      } else {
        this.suggestionStaffs = [];
        this.staffInfo = null;
      }
    });
  }

  addStaffToTextBox(event: Staff) {
    this.staffInfo = event;
  }

  calculate() {

  }
}
