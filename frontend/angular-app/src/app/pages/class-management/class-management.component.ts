import { Component, OnInit } from '@angular/core';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { CommonDialogService } from 'src/app/shared/components/common-detail-dialog/common-dialog.service';
import { EntityActionEvent } from 'src/app/shared/components/common-detail-dialog/common-entity-dialog-interface';
import { ClassDetailComponent } from './class-detail/class-detail.component';
import { ClassService } from './class-service/class.service';
import { Classes } from './class-service/classes-model';

@Component({
  selector: 'app-class-management',
  templateUrl: './class-management.component.html',
  styleUrls: ['./class-management.component.css']
})
export class ClassManagementComponent implements OnInit {

  classes: Classes[];
  selectedClass: any;
  cols: any[];

  constructor(private readonly dialog: CommonDialogService, private classService: ClassService) { }

  ngOnInit(): void {
    this.cols = [{ field: 'id', header: 'Id' },
    { field: 'className', header: 'Class Name' },
    { field: 'classCode', header: 'Class Code' },
    { field: 'startDate', header: 'Start Date' },
    { field: 'endDate', header: 'End Date' },
    { field: 'fee', header: 'Fee' }];

    this.classService.getClasses().subscribe(data => this.classes = data);
  }

  showDialogToAdd() {
    const result = this.dialog.openDialog('New Class', ClassDetailComponent, {});
    result.subscribe(this.updateTable);
  }

  delete(clas: any) {
    const index = this.classes.findIndex(cl => clas.id === cl.id);
    this.classes.splice(index, 1);
    this.classService.deleteClass(clas);
  }

  updateTable(event: EntityActionEvent<Classes>) {
    switch (event?.action) {
      case ENTITY_ACTION.CREATE:
        this.classes.push(event.entity);
        break;
      case ENTITY_ACTION.EDIT:
        const index = this.classes.findIndex(stu => event.entity.id === stu.id);
        if (index >= 0) {
          this.classes[index] = event.entity;
        }
        break;
      case ENTITY_ACTION.DELETE:
        this.classes.splice(this.classes.findIndex(stu => event.entity.id === stu.id), 1);
        break;
    }
  }

  save(result: any, isNewClasses?: boolean) {
    if (isNewClasses) {
      this.classService.createClass(result.clas).subscribe(newClass => this.classes.push(newClass));
    } else {
      if (result.isClassInfoChange) {
        if (result.attendance && result.attendance.length > 0) {
          // Update class and attendance
          console.log("update class and attendance");
          this.classService.updateClassAndAttendance(result.clas, result.attendance).subscribe(item => {
            const index = this.classes.findIndex(cl => item.id === cl.id);
            if (index >= 0) {
              this.classes[index] = item;
            }
          });

        } else {
          // Update class
          console.log("update class");
          this.classService.updateClass(result.clas).subscribe(item => {
            const index = this.classes.findIndex(cl => item.id === cl.id);
            if (index >= 0) {
              this.classes[index] = item;
            }
          });
        }
      } else {
        if (result.attendance.length > 0) {
          console.log("update attendace");
          // Update attendance
          this.classService.updateAttedance(result.clas, result.attendance).subscribe();
        }
      }
    }
  }

  onRowSelect(event: any) {
    const result = this.dialog.openDialog('Class Detail', ClassDetailComponent, { ...event.data });
    result.subscribe(this.updateTable);
  }

}
