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
  fields: any[];

  constructor(public dialogRef: MatDialogRef<StudentDetailComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.isNewStudent = Object.keys(data.student).length === 0;
    this.studentInfo = data.student;
    this.studentInfo.birthday = new Date(data.student.birthday);
    this.title = data.title;

    this.fields = [{ field: 'fullName', header: 'Full Name', type: 'text' },
    { field: 'birthday', header: 'Birth Day', type: 'date' },
    { field: 'email', header: 'Email', type: 'email' },
    { field: 'facebook', header: 'Facebook', type: 'text' },
    { field: 'contactNumber', header: 'Phone Number', type: 'tel' }];
    if (!this.isNewStudent) {
      this.fields.unshift({ field: 'id', header: 'StudentId', type: 'number' });
    }
  }

  ngOnInit(): void {
  }

  closeDialog(action: string) {
    this.dialogRef.close({ action, student: this.studentInfo });
  }
}
