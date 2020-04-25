import { Component, OnInit, EventEmitter } from '@angular/core';
import { ClassService } from '../../class-management/class-service/class.service';
import { Class } from '../../class-management/class-service/class';
import { Student } from '../student-service/student';
// tslint:disable-next-line:max-line-length
import { CommonEntityDialogInterface, EntityActionEvent } from '../../../shared/components/common-detail-dialog/common-entity-dialog-interface';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { ActivatedRoute } from '@angular/router';
import { StudentService } from '../student-service/student.service';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit, CommonEntityDialogInterface<Student> {

  studentInfo: Student;
  title: string;
  isNewStudent: boolean;
  fields: any[];
  classes: Class[];
  event: EventEmitter<EntityActionEvent<Student>> = new EventEmitter();
  action = ENTITY_ACTION;

  constructor(private classService: ClassService, private route: ActivatedRoute, private studentService: StudentService) {
    this.fields = [{ field: 'fullName', header: 'Full Name', cols: 2, required: 'true' },
    { field: 'birthday', header: 'Birthday', cols: 1 },
    { field: 'email', header: 'Email', cols: 2 },
    { field: 'contactNumber', header: 'Phone Number', cols: 1 },
    { field: 'facebook', header: 'Facebook', cols: 2 },
    { field: 'occupation', header: 'Ocupation', cols: 1 }];
  }

  setEntityDialogData(title: string, isNewEntity: boolean, entity: any): void {
    this.title = title;
    this.isNewStudent = isNewEntity;
    this.studentInfo = entity;
    if (!this.isNewStudent) {
      this.fields.unshift({ field: 'id', header: 'StudentId', cols: 1 });
    }
    this.loadClassData();
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.studentService.getStudent(+id).subscribe((student: Student) => {
        this.studentInfo = student;
        this.loadClassData();
      });
      this.isNewStudent = false;
    }
  }

  loadClassData() {
    if (!this.classes && this.studentInfo?.id) {
      this.classService.getClassByStudent(this.studentInfo.id).subscribe(result => this.classes = result);
    }
  }

  sendEvent(action: ENTITY_ACTION) {
    switch (action) {
      case ENTITY_ACTION.CREATE:
        this.studentService.createStudent(this.studentInfo).subscribe();
        break;
      case ENTITY_ACTION.EDIT:
        this.studentService.updateStudent(this.studentInfo).subscribe();
        break;
      case ENTITY_ACTION.DELETE:
        this.studentService.deleteStudent(this.studentInfo.id).subscribe();
        break;
    }

    this.event.emit({ action, entity: this.studentInfo });
  }

  getEvent(): EventEmitter<EntityActionEvent<Student>> {
    return this.event;
  }
}
