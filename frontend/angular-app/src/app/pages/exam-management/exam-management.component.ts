import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api/selectitem';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { CommonDialogService } from 'src/app/shared/components/common-detail-dialog/common-dialog.service';
import { EntityActionEvent } from 'src/app/shared/components/common-detail-dialog/common-entity-dialog-interface';
import { ExportService } from 'src/app/shared/service/export.service';
import { ClassService } from '../class-management/class-service/class.service';
import { Classes } from '../class-management/class-service/classes-model';
import { Student } from '../student-management/student-service/student';
import { StudentService } from '../student-management/student-service/student.service';
import { ExamDetailComponent } from './exam-detail/exam-detail.component';
import { ExamResultComponent } from './exam-result/exam-result.component';
import { Exam, ExamResult } from './exam-service/exam.model';
import { ExamService } from './exam-service/exam.service';

@Component({
  selector: 'app-exam-management',
  templateUrl: './exam-management.component.html',
  styleUrls: ['./exam-management.component.css']
})
export class ExamManagementComponent implements OnInit {

  // tab 1
  exams: Exam[];
  selectedExam: Exam;
  examFields: any[];

  // tab 2
  searchOptions: SelectItem[];
  selectedType: string;
  suggestionClasses: Classes[];
  classData: Classes;
  suggestionExams: Exam[];
  examData: any;
  suggestionStudents: Student[];
  studentData: Student;
  examResults: ExamResult[];
  filterClasses: Classes[];
  selectedClass: Classes;
  filterStudents: Student[];
  selectedStudent: Student;

  // tab 3
  suggestionClasses2: Classes[];
  classData2: Classes;
  suggestionExams2: Exam[];
  examData2: Exam;
  examResults2: ExamResult[];
  selectedResult: ExamResult;

  constructor(private readonly dialog: CommonDialogService, private examService: ExamService,
    private classService: ClassService, private studentService: StudentService,
    private exportService: ExportService) {

    // tab 1
    this.examFields = [
      { field: 'name', header: 'Name' },
      { field: 'examType', header: 'Type' },
      { field: 'description', header: 'Description' }
    ];

    // tab 2
    this.searchOptions = [
      { label: 'Class', value: 'Class' },
      { label: 'Student', value: 'Student' },
    ];

    this.selectedType = 'Class';
  }

  ngOnInit(): void {
    this.examService.getExams().subscribe(data => {
      this.exams = data
    });
  }

  /******************* TAB 1 *******************/
  showDialogToAdd() {
    const result = this.dialog.openDialog('New Exam', ExamDetailComponent, {});
    result.subscribe(evt => this.updateTable(evt, this.exams));
  }

  delete(exam: Exam) {
    const index = this.exams.findIndex(ex => exam.id === ex.id);
    this.exams.splice(index, 1);
    this.examService.deleteExam(exam.id);
  }

  updateTable(event: EntityActionEvent<Exam>, exams: Exam[]) {
    switch (event?.action) {
      case ENTITY_ACTION.CREATE:
        exams.push(event.entity);
        break;
      case ENTITY_ACTION.EDIT:
        const index = this.exams.findIndex(ex => event.entity.id === ex.id);
        if (index >= 0) {
          exams[index] = event.entity;
        }
        break;
      case ENTITY_ACTION.DELETE:
        exams.splice(this.exams.findIndex(ex => event.entity.id === ex.id), 1);
        break;
    }
  }

  onRowSelect(event: any) {
    const result = this.dialog.openDialog('Exam Detail', ExamDetailComponent, { ...event.data });
    result.subscribe(evt => this.updateTable(evt, this.exams));
  }

  /******************* TAB 2 *******************/
  searchClass(event: any) {
    this.classService.searchClass(event.query).subscribe(data => {
      if (data) {
        this.suggestionClasses = data;
      } else {
        this.suggestionClasses = [];
        this.classData = null;
      }
    });
  }

  searchStudent(event: any) {
    this.studentService.searchStudent(event.query).subscribe(data => {
      if (data) {
        this.suggestionStudents = data;
        console.log(data);
      } else {
        this.suggestionStudents = [];
        this.studentData = null;
      }
    });
  }

  searchExam(event: any) {
    this.examService.searchExam(event.query).subscribe(data => {
      if (data) {
        this.suggestionExams = data;
      } else {
        this.suggestionExams = [];
        this.examData = null;
      }
    });
  }

  searchResult() {
    let classId = this.classData ? this.classData.id.toString() : "";
    let studentId = this.studentData ? this.studentData.id.toString() : "";
    let examId = this.examData ? this.examData.id.toString() : "";
    this.examService.searchResult(classId, studentId, examId).subscribe(data => {
      if (data) {
        this.examResults = data;
      } else {
        this.examResults = null;
      }
    });
  }

  addStudentToSelectBox(event: Classes) {
    this.filterStudents = event.studentInfos;
  }

  addClassToSelectBox(event: Student) {
    this.filterClasses = event.classes;
  }

  clearSearchData() {
    this.suggestionClasses = null;
    this.suggestionExams = null;
    this.suggestionStudents = null;
    this.classData = null;
    this.examData = null;
    this.studentData = null;
    this.examResults = null;
    this.filterClasses = null;
    this.selectedClass = null;
    this.filterStudents = null;
    this.selectedStudent = null;
  }

  export() {
    this.exportService.exportExcel(this.exams, 'exams');
  }

  /******************* TAB 3 *******************/
  searchClass2(event: any) {
    this.classService.searchClass(event.query).subscribe(data => {
      if (data) {
        this.suggestionClasses2 = data;
      } else {
        this.suggestionClasses2 = [];
        this.classData2 = null;
      }
    });
  }

  searchExam2(event: any) {
    this.examService.searchExam(event.query).subscribe(data => {
      if (data) {
        this.suggestionExams2 = data;
      } else {
        this.suggestionExams2 = [];
        this.examData2 = null;
      }
    });
  }

  searchResult2() {
    let classId = this.classData2 ? this.classData2.id.toString() : "";
    let studentId = "";
    let examId = this.examData2 ? this.examData2.id.toString() : "";
    this.examService.getListStudentResult(classId, studentId, examId).subscribe(data => {
      if (data) {
        this.examResults2 = data;
      } else {
        this.examResults2 = null;
      }
    });
  }

  clearSearchData2() {
    this.suggestionClasses2 = null;
    this.suggestionExams2 = null;
    this.classData2 = null;
    this.examData2 = null;
    this.examResults2 = null;
  }

  onRowSelect2(event: any) {
    const result = this.dialog.openDialog('Exam Result', ExamResultComponent, { ...event.data });
    result.subscribe(evt => {
      if (evt) {
        this.updateTable2(evt, this.examResults2)
      }
    });
  }

  updateTable2(event: EntityActionEvent<ExamResult>, examResults2: ExamResult[]) {
    let index = -1;
    if (event.entity.id) {
      index = this.examResults2.findIndex(ex => event.entity.id === ex.id);
    } else {
      index = this.examResults2.findIndex(ex => event.entity.studentInfo.fullName === ex.studentInfo.fullName &&
        event.entity.studentInfo.birthday === ex.studentInfo.birthday);
    }
    if (index >= 0) {
      examResults2[index] = event.entity;
    }
  }

  update() {
    let updateResult: ExamResult[];
    updateResult = [];
    this.examResults2.forEach(ex => {
      if (ex.id) {
        updateResult.push(ex);
      } else {
        if (ex.listening || ex.speaking || ex.reading || ex.writing) {
          updateResult.push(ex);
        }
      }
    });
    this.examService.updateExamResult(updateResult).subscribe(data => {
      if (data) {
        this.examResults2 = data;
      } else {
        this.examResults2 = null;
      }
    });
  }

}
