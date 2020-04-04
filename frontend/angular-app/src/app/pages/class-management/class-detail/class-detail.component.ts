import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-class-detail',
  templateUrl: './class-detail.component.html',
  styleUrls: ['./class-detail.component.css']
})
export class ClassDetailComponent implements OnInit {

  classInfo: any;
  title: string;
  isNewClass: boolean;
  fields: any[];

  constructor(public dialogRef: MatDialogRef<ClassDetailComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.isNewClass = Object.keys(data.clas).length === 0;
    this.classInfo = data.clas;
    this.classInfo.startDate = new Date(data.clas.startDate);
    this.classInfo.endDate = new Date(data.clas.endDate);
    this.title = data.title;

    this.fields = [{ field: 'id', header: 'Id', type: 'text' },
    { field: 'className', header: 'Class Name', type: 'text' },
    { field: 'classCode', header: 'Class Code', type: 'text' },
    { field: 'startDate', header: 'Start Date', type: 'date' },
    { field: 'endDate', header: 'End Date', type: 'date' },
    { field: 'fee', header: 'Fee', type: 'text' }];
    if (!this.isNewClass) {
      this.fields.unshift({ field: 'id', header: 'ClassId', type: 'number' });
    }
  }

  ngOnInit(): void {
  }

  closeDialog(action: string) {
    this.dialogRef.close({ action, student: this.classInfo });
  }
}
