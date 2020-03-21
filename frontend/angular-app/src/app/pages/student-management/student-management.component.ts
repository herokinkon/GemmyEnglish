import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { StudentDetailComponent } from './student-detail/student-detail.component';
import { Student } from './student-service/student';
import { StudentService } from './student-service/student.service';

@Component({
  selector: 'app-student-management',
  templateUrl: './student-management.component.html',
  styleUrls: ['./student-management.component.css']
})
export class StudentManagementComponent implements OnInit {

  students: Student[];
  selectedStudent: any;
  cols: any[];

  constructor(public dialog: MatDialog, private studentService: StudentService) { }

  ngOnInit(): void {
    this.cols = [{ field: 'id', header: 'StudentId' },
    { field: 'fullName', header: 'Full Name' },
    { field: 'birthday', header: 'Birth Day', pipe: 'date' },
    { field: 'email', header: 'Email' },
    { field: 'facebook', header: 'Facebook' },
    { field: 'contactNumber', header: 'Phone Number' }];

    // const dod = Date.now();
    // this.students = [{ id: 1, name: 'Adam', birthday: dod, email: 'test@gmail', fb: 'fb.com/hungdo', contact_number: '123456789' },
    // { id: 2, name: 'Tina', birthday: dod, email: 'test@gmail', fb: 'fb.com/hungdo', contact_number: '123456789' },
    // { id: 3, name: 'Mark', birthday: dod, email: 'test@gmail', fb: 'fb.com/hungdo', contact_number: '123456789' },
    // { id: 4, name: 'Lovelance', birthday: dod, email: 'test@gmail', fb: 'fb.com/hungdo', contact_number: '123456789' }];
    this.studentService.getStudents().subscribe(data => this.students = data);

  }

  showDialogToAdd() {
    const dialogRef = this.dialog.open(StudentDetailComponent, { data: { title: 'New Student', student: {} } });
    dialogRef.afterClosed().subscribe(data => { if (data) { this.save(data.student, true); } });
  }

  delete(student: any) {
    const index = this.students.findIndex(stu => student.id === stu.id);
    this.students.splice(index, 1);
    this.studentService.deleteStudent(student);
  }

  save(student: any, isNewStudent?: boolean) {
    if (isNewStudent) {
      this.studentService.createStudent(student).subscribe(newStudent => this.students.push(newStudent));
    } else {
      this.studentService.updateStudent(student);
      const index = this.students.findIndex(stu => student.id === stu.id);
      if (index >= 0) {
        this.students[index] = student;
      }
    }
  }

  onRowSelect(event: any) {
    const dialogRef = this.dialog.open(StudentDetailComponent, { data: { title: 'Student Detail', student: { ...event.data } } });
    dialogRef.afterClosed().subscribe(result => { if (result) { this[result.action](result.student); } });
  }

}
