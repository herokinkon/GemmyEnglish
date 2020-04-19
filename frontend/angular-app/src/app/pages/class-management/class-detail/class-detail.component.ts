import { Component, EventEmitter, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { CommonEntityDialogInterface, EntityActionEvent } from 'src/app/shared/components/common-detail-dialog/common-entity-dialog-interface';
import { Student } from '../../student-management/student-service/student';
import { StudentService } from '../../student-management/student-service/student.service';
import { ClassService } from '../class-service/class.service';
import { Classes, Course } from '../class-service/classes-model';

@Component({
  selector: 'app-class-detail',
  templateUrl: './class-detail.component.html',
  styleUrls: ['./class-detail.component.css']
})
export class ClassDetailComponent implements OnInit, CommonEntityDialogInterface<Classes>  {

  event: EventEmitter<EntityActionEvent<Classes>> = new EventEmitter();
  action = ENTITY_ACTION;
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
  checkAttendance = false;

  constructor(private classService: ClassService, private route: ActivatedRoute, private studentService: StudentService) {
    this.fields = [{ field: 'className', header: 'Class Name', cols: 1 },
    { field: 'classCode', header: 'Class Code', cols: 1 },
    { field: 'startDate', header: 'Start Date', cols: 1 },
    { field: 'endDate', header: 'End Date', cols: 1 },
    { field: 'fee', header: 'Fee', cols: 1 },
    { field: 'courseId', header: 'Course', cols: 1 }];
  }

  setEntityDialogData(title: string, isNewEntity: boolean, entity: Classes): void {
    this.title = title;
    this.isNewClass = isNewEntity;
    this.classInfo = entity;
    if (!this.isNewClass) {
      this.fields.unshift({ field: 'id', header: 'Id', type: 'text' });
    }
    this.getStudentData();
  }

  getEvent(): EventEmitter<EntityActionEvent<Classes>> {
    return this.event;
  }

  ngOnInit(): void {
    this.classService.getCourse().subscribe(data => {
      if (data) {
        this.course = data;
      }
    });
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.classService.getClassesById(+id).subscribe((cl: Classes) => {
        this.classInfo = cl;
        this.getStudentData();
      });
      this.isNewClass = false;
    }
  }

  getStudentData() {
    this.studentService.getStudentListByClass(this.classInfo.id).subscribe(result => {
      if (result) {
        this.studentList = result;
      }
    });
  }

  sendEvent(action: ENTITY_ACTION) {
    switch (action) {
      case ENTITY_ACTION.CREATE:
        this.classService.createClass(this.classInfo).subscribe();
        break;
      case ENTITY_ACTION.EDIT:
        this.save();
        // this.classService.updateClass(this.classInfo).subscribe();
        break;
      case ENTITY_ACTION.DELETE:
        this.classService.deleteClass(this.classInfo.id).subscribe();
        break;
    }

    this.event.emit({ action, entity: this.classInfo });
  }

  save() {
    if (this.isClassInfoChange) {
      if (this.isAttendanceChange) {
        // Update class and attendance
        console.log("update class and attendance");
        this.classService.updateClassAndAttendance(this.classInfo, this.studentList).subscribe();
      } else {
        // Update class
        console.log("update class");
        this.classService.updateClass(this.classInfo).subscribe();
      }
    } else {
      if (this.isAttendanceChange) {
        console.log("update attendace");
        // Update attendance
        this.classService.updateAttedance(this.classInfo, this.studentList).subscribe();
      }
    }
  }

  onChange() {
    this.isClassInfoChange = true;
  }

  attendanceChange() {
    this.isAttendanceChange = true;
  }

  courseChange(event: any) {
    if (!this.isClassInfoChange && !this.isAttendanceChange) {
      if (!event && this.classInfo.courseId != this.courseId) {
        this.onChange();
      }
    }
  }

}
