import { Component, EventEmitter, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { CommonEntityDialogInterface, EntityActionEvent } from 'src/app/shared/components/common-detail-dialog/common-entity-dialog-interface';
import { Student } from '../../student-management/student-service/student';
import { StudentService } from '../../student-management/student-service/student.service';
import { ClassService } from '../class-service/class.service';
import { Classes } from '../class-service/classes-model';
import { Staff } from '../../staff-management/staff-service/staff';
import { StaffService } from '../../staff-management/staff-service/staff.service';
import * as moment from 'moment'
import { WeekDay } from '@angular/common';

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
  // Student List 
  studentList: Student[];
  // Attendance 
  selectedStudent: Student[];
  isAttendanceChange: boolean = false;
  checkAttendance = false;
  // other info
  otherInfo: any[];
  // Staff List
  staffList: Staff[];
  schedule: string[] = [];

  constructor(private classService: ClassService, private route: ActivatedRoute,
    private studentService: StudentService, private staffService: StaffService) {
    this.fields = [{ field: 'className', header: 'Class Name' },
    { field: 'classCode', header: 'Class Code' },
    { field: 'fee', header: 'Fee' },
    { field: 'lesson', header: 'Lesson' },
    { field: 'startDate', header: 'Start Date' },
    { field: 'endDate', header: 'End Date' },
    { field: 'schedule', header: 'Schedule' }];

    // other info tab
    this.otherInfo = [{ field: 'campus', header: 'Campus' },
    { field: 'address', header: 'Address' },
    { field: 'room', header: 'Room' }];
  }

  setEntityDialogData(title: string, isNewEntity: boolean, entity: Classes): void {
    this.title = title;
    this.isNewClass = isNewEntity;
    this.classInfo = entity;
    if (this.classInfo.schedule) {
      this.schedule = this.classInfo.schedule.split(",");
    }
    this.getStudentData();
    this.getStaffData();
  }

  getEvent(): EventEmitter<EntityActionEvent<Classes>> {
    return this.event;
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.classService.getClassesById(+id).subscribe((cl: Classes) => {
        this.classInfo = cl;
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
        this.classService.updateClassAndAttendance(this.classInfo, this.studentList).subscribe();
      } else {
        // Update class
        this.classService.updateClass(this.classInfo).subscribe();
      }
    } else {
      if (this.isAttendanceChange) {
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

  getStaffData() {
    this.staffService.getStaffListByClass(this.classInfo.id).subscribe(result => {
      if (result) {
        this.staffList = result;
      }
    });
  }

  updateSchedule(event: string[]) {
    this.schedule = event;
    this.classInfo.schedule = event.join();
    this.calculateEndDate();
    this.onChange();
  }

  calculateEndDate() {
    this.onChange();
    if (this.classInfo.startDate && this.classInfo.lesson && this.schedule && this.schedule.length) {
      let start = moment(this.classInfo.startDate);
      let count = 1;
      var arr = [];
      let tmp = start.clone().day(WeekDay[this.schedule[0]]);
      let tmp2: moment.Moment;
      while (count < this.classInfo.lesson) {
        if (count + this.schedule.length > this.classInfo.lesson) {
          break;
        }
        tmp.add(7, 'days');
        count = count + this.schedule.length;
      }
      if (count == this.classInfo.lesson) {
        arr.push(tmp.format('yyyy-MM-DD'));
      } else {
        tmp2 = tmp.clone().day(WeekDay[this.schedule[this.classInfo.lesson - count]]);
        if (tmp2.isAfter(tmp, 'd')) {
          arr.push(tmp2.format('YYYY-MM-DD'));
        }
      }
      this.classInfo.endDate = arr[0];
    }
  }

}
