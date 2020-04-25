import { Component, OnInit } from '@angular/core';
import { CommonDialogService } from 'src/app/shared/components/common-detail-dialog/common-dialog.service';
import { ExamService } from './exam-service/exam.service';
import { Exam } from './exam-service/exam.model';
import { ExamDetailComponent } from './exam-detail/exam-detail.component';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { EntityActionEvent } from 'src/app/shared/components/common-detail-dialog/common-entity-dialog-interface';

@Component({
  selector: 'app-exam-management',
  templateUrl: './exam-management.component.html',
  styleUrls: ['./exam-management.component.css']
})
export class ExamManagementComponent implements OnInit {

  constructor(private readonly dialog: CommonDialogService, private examService: ExamService) { }

  exams: Exam[];
  selectedExam: Exam;
  examFields: any[];

  ngOnInit(): void {
    this.examFields = [{ field: 'name', header: 'Name'},
        { field: 'examType', header: 'Type'},
        { field: 'description', header: 'Description'}]

    this.examService.getExams().subscribe(data => {
      this.exams = data
      console.log(data)
    });
  }

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

}
