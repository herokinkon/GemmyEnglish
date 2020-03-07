import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { StudentDetailComponent } from './student-detail/student-detail.component';

@Component({
  selector: 'app-student-management',
  templateUrl: './student-management.component.html',
  styleUrls: ['./student-management.component.css']
})
export class StudentManagementComponent implements OnInit {

  students: any[];
  selectedStudent: any;
  cols: any[];

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
    this.cols = [{ field: 'id', header: 'StudentId' },
    { field: 'name', header: 'Full Name' },
    { field: 'phone', header: 'Phone Number' }];

    this.students = [{ id: '1', name: 'Adam', phone: '123456789' },
    { id: '2', name: 'Tina', phone: '123456789' },
    { id: '3', name: 'Mark', phone: '123456789' },
    { id: '4', name: 'Lovelance', phone: '123456789' }];
  }

  showDialogToAdd() {
    const dialogRef = this.dialog.open(StudentDetailComponent, { data: { title: 'New Student', student: {} } });
    dialogRef.afterClosed().subscribe(data => { if (data) { this.save(data.student, true); } });
  }

  delete(student: any) {
    const index = this.students.findIndex(stu => student.id === stu.id);
    this.students.splice(index, 1);
  }

  save(student: any, isNewStudent?: boolean) {
    if (isNewStudent) {
      this.students.push(student);
    } else {
      const index = this.students.findIndex(stu => student.id === stu.id);
      if (index >= 0) {
        this.students[index] = student;
      }
    }
  }

  onRowSelect(event: any) {
    const dialogRef = this.dialog.open(StudentDetailComponent, { data: { title: 'Student Detail', student: {...event.data} } });
    dialogRef.afterClosed().subscribe(result => { if (result) { this[result.action](result.student); } });
  }

}
