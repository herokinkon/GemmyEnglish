import { Component, OnInit } from '@angular/core';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { CommonDialogService } from 'src/app/shared/components/common-detail-dialog/common-dialog.service';
import { EntityActionEvent } from 'src/app/shared/components/common-detail-dialog/common-entity-dialog-interface';
import { Staff } from '../staff-management/staff-service/staff';
import { OtherOutcomeDetailComponent } from './other-outcome-detail/other-outcome-detail.component';
import { OtherOutcome } from './salary-service/salary';
import { SalaryService } from './salary-service/salary.service';

@Component({
  selector: 'salary-management',
  templateUrl: './salary-management.component.html',
  styleUrls: ['./salary-management.component.css']
})
export class SalaryManagementComponent implements OnInit {

  // tab 1
  fullTimeHeader: any[];
  fullTimeSalaries: Staff[]
  selectedFullTimeSalary: any;

  // tab 2
  partTimeHeader: any[];
  partTimeSalaries: Staff[]
  selectedPartTimeSalary: any;

  // tab 3
  othersHeader: any[];
  othersOutcome: OtherOutcome[]
  selectedOutcome: any;

  constructor(private readonly dialog: CommonDialogService, private salaryService: SalaryService) { }

  ngOnInit(): void {
    this.fullTimeHeader = [{ field: 'fullName', header: 'Full Name' },
    { field: 'birthday', header: 'Birthday' },
    { field: 'salary', header: 'Salary (VND/Month)' }];

    // this.partTimeHeader = [{ field: 'fullName', header: 'Full Name' },
    // { field: 'birthday', header: 'Birthday' },
    // { field: 'salary', header: 'Salary (VND/Hour)' },
    // { field: 'workedHour', header: 'Worked Hour' },
    // { field: 'actual', header: 'Actual Worked Hour' }];

    this.othersHeader = [{ field: 'fullName', header: 'Full Name' },
    { field: 'birthday', header: 'Birthday' },
    { field: 'usedDate', header: 'Date' },
    { field: 'purpose', header: 'Purpose' },
    { field: 'cost', header: 'Cost' }];

    this.salaryService.getFullTimeStaffSalary().subscribe(data => this.fullTimeSalaries = data);
    // this.salaryService.getPartTimeStaffSalary().subscribe(data => this.partTimeSalaries = data);
    this.salaryService.getOtherOutcome().subscribe(data => this.othersOutcome = data);
  }

  // showDialogToAdd() {
  //   const result = this.dialog.openDialog('New Salary', SalaryDetailComponent, {});
  //   result.subscribe(evt => this.updateTable(evt, this.fullTimeSalaries));
  // }

  // delete(salary: any) {
  //   const index = this.fullTimeSalaries.findIndex(sal => salary.id === sal.id);
  //   this.fullTimeSalaries.splice(index, 1);
  //   this.salaryService.deleteStaffSalary(salary);
  // }

  // updateTable(event: EntityActionEvent<Staff>, fullTimeSalaries: Staff[]) {
  //   switch (event?.action) {
  //     case ENTITY_ACTION.CREATE:
  //       fullTimeSalaries.push(event.entity);
  //       break;
  //     case ENTITY_ACTION.EDIT:
  //       const index = this.fullTimeSalaries.findIndex(stu => event.entity.id === stu.id);
  //       if (index >= 0) {
  //         fullTimeSalaries[index] = event.entity;
  //       }
  //       break;
  //     case ENTITY_ACTION.DELETE:
  //       fullTimeSalaries.splice(this.fullTimeSalaries.findIndex(stu => event.entity.id === stu.id), 1);
  //       break;
  //   }
  // }

  // onRowSelect(event: any) {
  //   const result = this.dialog.openDialog('Salary Detail', SalaryDetailComponent, { ...event.data });
  //   result.subscribe(evt => this.updateTable(evt, this.fullTimeSalaries));
  // }

  ///////////// Other Outcome tab
  showDialogToAddOtherOutcome() {
    const result = this.dialog.openDialog('New Other Outcome', OtherOutcomeDetailComponent, {});
    result.subscribe(evt => this.updateOtherOutcomeTable(evt, this.othersOutcome));
  }

  otherOutcomeSelect(event: any) {
    const result = this.dialog.openDialog('Other Outcome Detail', OtherOutcomeDetailComponent, { ...event.data });
    result.subscribe(evt => this.updateOtherOutcomeTable(evt, this.othersOutcome));
  }

  updateOtherOutcomeTable(event: EntityActionEvent<OtherOutcome>, othersOutcome: OtherOutcome[]) {
    switch (event?.action) {
      case ENTITY_ACTION.CREATE:
        othersOutcome.push(event.entity);
        break;
      case ENTITY_ACTION.EDIT:
        const index = this.othersOutcome.findIndex(stu => event.entity.id === stu.id);
        if (index >= 0) {
          othersOutcome[index] = event.entity;
        }
        break;
      case ENTITY_ACTION.DELETE:
        othersOutcome.splice(this.othersOutcome.findIndex(stu => event.entity.id === stu.id), 1);
        break;
    }
  }
}
