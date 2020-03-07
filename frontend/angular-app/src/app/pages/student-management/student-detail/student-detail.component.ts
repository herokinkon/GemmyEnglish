import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit {

  studentInfo: any;
  title: string;
  isNewStudent: boolean;

  constructor(public dialogRef: MatDialogRef<StudentDetailComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.isNewStudent = Object.keys(data.student).length === 0;
    this.studentInfo = data.student;
    this.title = data.title;
  }

  ngOnInit(): void {
  }

  closeDialog(action: string) {
    this.dialogRef.close({ action, student: this.studentInfo });
  }
}
