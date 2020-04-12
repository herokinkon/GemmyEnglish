import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ClassService } from '../../class-management/class-service/class.service';
import { Class } from '../../class-management/class-service/class';
import { Student } from '../student-service/student';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit {

  studentInfo: Student;
  title: string;
  isNewStudent: boolean;
  fields: any[];
  classes: Class[];

  constructor(public dialogRef: MatDialogRef<StudentDetailComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private classService: ClassService) {
    this.isNewStudent = Object.keys(data.student).length === 0;
    this.studentInfo = data.student;
    this.studentInfo.birthday = new Date(data.student.birthday);
    this.title = data.title;

    this.fields = [{ field: 'fullName', header: 'Full Name', cols: 2 },
    { field: 'birthday', header: 'Birthday', cols: 1 },
    { field: 'email', header: 'Email', cols: 2 },
    { field: 'contactNumber', header: 'Phone Number', cols: 1 },
    { field: 'facebook', header: 'Facebook', cols: 2 }];
    if (!this.isNewStudent) {
      this.fields.unshift({ field: 'id', header: 'StudentId', cols: 1 });
    }
  }

  ngOnInit(): void {
    this.loadClassData();
  }

  closeDialog(action: string) {
    this.dialogRef.close({ action, student: this.studentInfo });
  }

  loadClassData() {
    if (!this.classes) {
      this.classService.getClassByStudent(this.studentInfo.id).subscribe(result => this.classes = result);
    }
  }
}
