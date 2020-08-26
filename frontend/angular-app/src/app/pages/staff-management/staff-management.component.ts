import { Component, OnInit } from '@angular/core';
import { Staff } from './staff-service/staff';
import { StaffService } from './staff-service/staff.service';
import { CommonDialogService } from 'src/app/shared/components/common-detail-dialog/common-dialog.service';
import { StaffDetailComponent } from './staff-detail/staff-detail.component';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { EntityActionEvent } from 'src/app/shared/components/common-detail-dialog/common-entity-dialog-interface';

@Component({
  selector: 'app-staff-management',
  templateUrl: './staff-management.component.html',
  styleUrls: ['./staff-management.component.css']
})
export class StaffManagementComponent implements OnInit {

  staffs: Staff[];
  cols: any[];

  constructor(private readonly dialog: CommonDialogService, private staffservice: StaffService) { }

  ngOnInit(): void {
    this.cols = [
    { field: 'fullName', header: 'Full Name' },
    { field: 'birthday', header: 'Birthday' },
    { field: 'email', header: 'Email' },
    { field: 'facebook', header: 'Facebook' },
    { field: 'contactNumber', header: 'Phone Number' }];

    this.staffservice.getStaffs().subscribe(data => this.staffs = data);
  }

  updateTable(event: EntityActionEvent<Staff>) {
    switch (event?.action) {
      case ENTITY_ACTION.CREATE:
        if (!this.staffs) {
          this.staffs = [];
        }
        this.staffs.push(event.entity);
        break;
      case ENTITY_ACTION.EDIT:
        const index = this.staffs.findIndex(staff => event.entity.id === staff.id);
        if (index >= 0) {
          this.staffs[index] = event.entity;
        }
        break;
      case ENTITY_ACTION.DELETE:
        let startIndex = this.staffs.findIndex(staff => event.entity.id === staff.id);
        this.staffs.splice(startIndex, 1);
        break;
    }
  }

  showDialogToAdd() {
    const result = this.dialog.openDialog('New Staff', StaffDetailComponent, {});
    result.subscribe(evt => this.updateTable(evt));
  }
  onRowSelect(event: any) {
    const result = this.dialog.openDialog('Staff Detail', StaffDetailComponent, { ...event.data });
    result.subscribe(evt => this.updateTable(evt));
  }
}
