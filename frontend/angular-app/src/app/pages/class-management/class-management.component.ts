import { Component, OnInit } from '@angular/core';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { CommonDialogService } from 'src/app/shared/components/common-detail-dialog/common-dialog.service';
import { EntityActionEvent } from 'src/app/shared/components/common-detail-dialog/common-entity-dialog-interface';
import { Student } from '../student-management/student-service/student';
import { StudentService } from '../student-management/student-service/student.service';
import { ClassDetailComponent } from './class-detail/class-detail.component';
import { ClassService } from './class-service/class.service';
import { Classes } from './class-service/classes-model';

@Component({
  selector: 'app-class-management',
  templateUrl: './class-management.component.html',
  styleUrls: ['./class-management.component.css']
})
export class ClassManagementComponent implements OnInit {

  classes: Classes[];
  selectedClass: any;
  cols: any[];

  class1: Classes;
  class2: Classes;
  suggestionClasses1: Classes[];
  suggestionClasses2: Classes[];
  studentList1: Student[];
  studentList2: Student[];
  isChange: boolean;

  isMoveStudent: boolean;

  constructor(private readonly dialog: CommonDialogService, private classService: ClassService, private studentService: StudentService) { }

  ngOnInit(): void {
    this.cols = [
    { field: 'className', header: 'Class Name' },
    { field: 'classCode', header: 'Class Code' },
    { field: 'startDate', header: 'Start Date' },
    { field: 'endDate', header: 'End Date' },
    { field: 'fee', header: 'Fee' }];
    this.isMoveStudent = true;
    this.classService.getClasses().subscribe(data => this.classes = data);
  }

  showDialogToAdd() {
    const result = this.dialog.openDialog('New Class', ClassDetailComponent, {});
    result.subscribe(evt => this.updateTable(evt));
  }

  delete(clas: any) {
    const index = this.classes.findIndex(cl => clas.id === cl.id);
    this.classes.splice(index, 1);
    this.classService.deleteClass(clas);
  }

  updateTable(event: EntityActionEvent<Classes>) {
    switch (event?.action) {
      case ENTITY_ACTION.CREATE:
        if (!this.classes) {
          this.classes = [];
        }
        this.classes.push(event.entity);
        break;
      case ENTITY_ACTION.EDIT:
        const index = this.classes.findIndex(cl => event.entity.id === cl.id);
        if (index >= 0) {
          this.classes[index] = event.entity;
        }
        break;
      case ENTITY_ACTION.DELETE:
        let startIndex = this.classes.findIndex(cl => event.entity.id === cl.id);
        this.classes.splice(startIndex, 1);
        break;
    }
  }

  onRowSelect(event: any) {
    const result = this.dialog.openDialog('Class Detail', ClassDetailComponent, { ...event.data });
    result.subscribe(evt => this.updateTable(evt));
  }

  searchClass(event: any, isSourceClass: boolean) {
    this.classService.searchClass(event.query).subscribe(data => {
      if (isSourceClass) {
        if (data) {
          this.suggestionClasses1 = data;
        } else {
          this.suggestionClasses1 = [];
          this.class1 = null;
        }
      } else {
        if (data) {
          this.suggestionClasses2 = data;
        } else {
          this.suggestionClasses2 = [];
          this.class2 = null;
        }
      }
    });
  }

  updateStudentList(event: Classes, isSource: boolean) {
    if (isSource) {
      this.studentList1 = event.studentInfos;
    } else {
      this.studentList2 = event.studentInfos;
    }
  }

  updateStudentClass() {
    if (this.isMoveStudent) {
      this.classService.updateStudentClass(this.studentList1, this.studentList2, this.class1.id, this.class2.id).subscribe();
    } else {
      this.classService.updateStudentClass(null, this.studentList2, null, this.class2.id).subscribe();
    }
    this.isChange = false;
  }

  changeList() {
    this.isChange = true;
  }

  clearSearchData() {
    this.suggestionClasses1 = null;
    this.suggestionClasses2 = null;
    this.class1 = null;
    this.class2 = null;
    this.studentList1 = null;
    this.studentList2 = null;
    this.isChange = false;
  }

  handleChange(event: any) {
    console.log(event)
    // event.checked = true -> move student
    // event.checked = false -> add student
    this.isMoveStudent = event.checked;
    this.studentService.getNewStudents().subscribe(data => this.studentList1 = data);
  }

}
