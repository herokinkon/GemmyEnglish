import { Component, OnInit } from '@angular/core';
import { StudentDetailComponent } from './student-detail/student-detail.component';
import { Student } from './student-service/student';
import { StudentService } from './student-service/student.service';
import { CommonDialogService } from 'src/app/shared/components/common-detail-dialog/common-dialog.service';
import { EntityActionEvent } from 'src/app/shared/components/common-detail-dialog/common-entity-dialog-interface';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';

@Component({
  selector: 'app-student-management',
  templateUrl: './student-management.component.html',
  styleUrls: ['./student-management.component.css']
})
export class StudentManagementComponent implements OnInit {

  students: Student[];
  selectedStudent: any;
  cols: any[];

  constructor(private readonly dialog: CommonDialogService, private studentService: StudentService) { }

  ngOnInit(): void {
    this.cols = [
    { field: 'fullName', header: 'Full Name' },
    { field: 'birthday', header: 'Birthday' },
    { field: 'email', header: 'Email' },
    { field: 'facebook', header: 'Facebook' },
    { field: 'contactNumber', header: 'Phone Number' }];

    this.studentService.getStudents().subscribe(data => this.students = data);
  }

  showDialogToAdd() {
    const result = this.dialog.openDialog('New Student', StudentDetailComponent, {});
    result.subscribe(evt => this.updateTable(evt));
  }

  delete(student: any) {
    const index = this.students.findIndex(stu => student.id === stu.id);
    this.students.splice(index, 1);
    this.studentService.deleteStudent(student);
  }

  updateTable(event: EntityActionEvent<Student>) {
    switch (event?.action) {
      case ENTITY_ACTION.CREATE:
        if (!this.students) {
          this.students = [];
        } 
        this.students.push(event.entity);
        break;
      case ENTITY_ACTION.EDIT:
        const index = this.students.findIndex(stu => event.entity.id === stu.id);
        if (index >= 0) {
          this.students[index] = event.entity;
        }
        break;
      case ENTITY_ACTION.DELETE:
        let startIndex = this.students.findIndex(stu => event.entity.id === stu.id);
        this.students.splice(startIndex, 1);
        break;
    }
  }

  onRowSelect(event: any) {
    const result = this.dialog.openDialog('Student Detail', StudentDetailComponent, { ...event.data });
    result.subscribe(evt => this.updateTable(evt));
  }
}
