import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Classes } from './class-service/classes-model';
import { ClassService } from './class-service/class.service';
import { ClassDetailComponent } from './class-detail/class-detail.component';

@Component({
  selector: 'app-class-management',
  templateUrl: './class-management.component.html',
  styleUrls: ['./class-management.component.css']
})
export class ClassManagementComponent implements OnInit {

  classes: Classes[];
  selectedClass: any;
  cols: any[];

  constructor(public dialog: MatDialog, private classService: ClassService) { }

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
    const dialogRef = this.dialog.open(ClassDetailComponent, { data: { title: 'New Class', clas: {} } });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.save(result, true);
      }
    });
  }

  delete(clas: any) {
    const index = this.classes.findIndex(cl => clas.id === cl.id);
    this.classes.splice(index, 1);
    this.classService.deleteClass(clas);
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
    const dialogRef = this.dialog.open(ClassDetailComponent, { data: { title: 'Class Detail', clas: { ...event.data } } });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this[result.action](result, false);
      }
    });
  }

}
