import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Student } from '../../student-management/student-service/student';
import { StudentService } from '../../student-management/student-service/student.service';
import { ClassService } from '../class-service/class.service';
import { Classes, Course } from '../class-service/classes-model';

@Component({
  selector: 'app-class-detail',
  templateUrl: './class-detail.component.html',
  styleUrls: ['./class-detail.component.css']
})
export class ClassDetailComponent implements OnInit {

  // Class Info
  classInfo: Classes;
  title: string;
  isNewClass: boolean;
  fields: any[];
  isClassInfoChange: boolean = false;
  course: Course[];
  courseId: number;
  // Student List 
  studentList: Student[];
  // Attendance 
  selectedStudent: Student[];
  isAttendanceChange: boolean = false;

  constructor(public dialogRef: MatDialogRef<ClassDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private studentService: StudentService, private classService: ClassService) {
    this.isNewClass = Object.keys(data.clas).length === 0;
    this.classInfo = data.clas;
    this.classInfo.startDate = new Date(data.clas.startDate);
    this.classInfo.endDate = new Date(data.clas.endDate);
    this.title = data.title;
    this.courseId = data.clas.courseId;

    this.fields = [{ field: 'className', header: 'Class Name', type: 'text' },
    { field: 'classCode', header: 'Class Code', type: 'text' },
    { field: 'startDate', header: 'Start Date', type: 'date' },
    { field: 'endDate', header: 'End Date', type: 'date' },
    { field: 'fee', header: 'Fee', type: 'text' },
    { field: 'courseId', header: 'Course', type: 'text' }];
    if (!this.isNewClass) {
      this.fields.unshift({ field: 'id', header: 'Id', type: 'text' });
    }
  }

  ngOnInit(): void {
    this.getStudentData();
    this.classService.getCourse().subscribe(data => { 
      if (data) { 
        this.course = data;
      }
    });
  }

  closeDialog(action: string) {
    let result = { action, clas: this.classInfo, attendance: this.selectedStudent, 
      isClassInfoChange: this.isClassInfoChange };
    this.dialogRef.close(result);
  }

  getStudentData() {
    this.studentService.getStudentListByClass(this.classInfo.id).subscribe(result => {
      if (result) {
        this.studentList = result;
      }
    });
  }

  onChange() {
    this.isClassInfoChange = true;
  }

  onNgModelChange() {
    this.isAttendanceChange = true;
  }

  courseChange(event: any) {
    if (!this.isClassInfoChange && !this.isAttendanceChange) {
      if (!event && this.classInfo.courseId != this.courseId) {
        this.isClassInfoChange = true;
      }
    }
  }

}
