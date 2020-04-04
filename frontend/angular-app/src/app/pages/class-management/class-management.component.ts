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
    dialogRef.afterClosed().subscribe(data => { if (data) { this.save(data.clas, true); } });
  }

  delete(clas: any) {
    const index = this.classes.findIndex(cl => clas.id === cl.id);
    this.classes.splice(index, 1);
    this.classService.deleteClass(clas);
  }

  save(clas: any, isNewClasses?: boolean) {
    if (isNewClasses) {
      this.classService.createClass(clas).subscribe(newClass => this.classes.push(newClass));
    } else {
      this.classService.updateClass(clas).subscribe();
      const index = this.classes.findIndex(cl => clas.id === cl.id);
      if (index >= 0) {
        this.classes[index] = clas;
      }
    }
  }

  onRowSelect(event: any) {
    const dialogRef = this.dialog.open(ClassDetailComponent, { data: { title: 'Class Detail', clas: { ...event.data } } });
    dialogRef.afterClosed().subscribe(result => { if (result) { this[result.action](result.clas); } });
  }

}
