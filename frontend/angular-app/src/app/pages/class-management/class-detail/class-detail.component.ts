import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Student } from '../../student-management/student-service/student';
import { StudentService } from '../../student-management/student-service/student.service';

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
  attFields: any[];
  selectedStudent: String[];
  studentList: Student[];
  displayedColumns: string[] = ['position', 'name', 'birthday'];
  selectedStudents: Student[]

  constructor(public dialogRef: MatDialogRef<ClassDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private studentService: StudentService) {
    this.isNewClass = Object.keys(data.clas).length === 0;
    this.classInfo = data.clas;
    this.classInfo.startDate = new Date(data.clas.startDate);
    this.classInfo.endDate = new Date(data.clas.endDate);
    this.title = data.title;

    this.fields = [{ field: 'className', header: 'Class Name', type: 'text' },
    { field: 'classCode', header: 'Class Code', type: 'text' },
    { field: 'startDate', header: 'Start Date', type: 'date' },
    { field: 'endDate', header: 'End Date', type: 'date' },
    { field: 'fee', header: 'Fee', type: 'text' }];
    if (!this.isNewClass) {
      this.fields.unshift({ field: 'id', header: 'Id', type: 'text' });
    }
  }

  ngOnInit(): void {
    this.getStudentData();
  }

  closeDialog(action: string) {
    this.dialogRef.close({ action, class: this.classInfo, attendance: this.selectedStudent});
  }

  getStudentData() {
    this.studentService.getStudentListByClass(this.classInfo.id).subscribe(result => {
      this.studentList = result; 
      console.log(this.studentList);
    });
  }

}
