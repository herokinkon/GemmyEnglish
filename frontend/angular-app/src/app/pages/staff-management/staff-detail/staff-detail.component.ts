import { Component, OnInit, EventEmitter } from '@angular/core';
import { Staff } from '../staff-service/staff';
// tslint:disable-next-line:max-line-length
import { CommonEntityDialogInterface, EntityActionEvent } from 'src/app/shared/components/common-detail-dialog/common-entity-dialog-interface';
import { Class } from '../../class-management/class-service/class';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { StaffService } from '../staff-service/staff.service';

@Component({
  selector: 'app-staff-detail',
  templateUrl: './staff-detail.component.html',
  styleUrls: ['./staff-detail.component.css']
})
export class StaffDetailComponent implements OnInit, CommonEntityDialogInterface<Staff> {

  staffInfo: Staff;
  title: string;
  isNewStaff: boolean;
  fields: any[];
  classes: Class[];
  event: EventEmitter<EntityActionEvent<Staff>> = new EventEmitter();
  action = ENTITY_ACTION;
  workOfStaff = [];

  otherInfo: any[];

  constructor(private staffService: StaffService) {
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

  ngOnInit(): void {
  }

  setEntityDialogData(title: string, isNewEntity: boolean, entity: any): void {
    this.title = title;
    this.isNewStaff = isNewEntity;
    this.staffInfo = entity;
    if (this.staffInfo.workOfStaff) {
      this.workOfStaff = this.staffInfo.workOfStaff.split(',');
    }
    // if (!this.isNewStaff) {
    //   this.fields.unshift({ field: 'id', header: 'StaffId', cols: 1 });
    // }
  }

  sendEvent(action: ENTITY_ACTION) {
    switch (action) {
      case ENTITY_ACTION.CREATE:
        this.staffService.createStaff(this.staffInfo).subscribe();
        break;
      case ENTITY_ACTION.EDIT:
        this.staffService.updateStaff(this.staffInfo).subscribe();
        break;
      case ENTITY_ACTION.DELETE:
        this.staffService.deleteStaff(this.staffInfo.id).subscribe();
        break;
    }

    this.event.emit({ action, entity: this.staffInfo });
  }

  getEvent(): EventEmitter<EntityActionEvent<Staff>> {
    return this.event;
  }

  updateWorkOfStaff(works: string[]) {
    this.staffInfo.workOfStaff = works.join();
  }

  getWorkOfStaff() {
    return this.staffInfo.workOfStaff.split(',');
  }
}
