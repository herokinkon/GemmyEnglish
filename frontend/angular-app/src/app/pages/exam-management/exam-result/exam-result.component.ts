import { Component, EventEmitter, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { CommonEntityDialogInterface, EntityActionEvent } from '../../../shared/components/common-detail-dialog/common-entity-dialog-interface';
import { Student } from '../../student-management/student-service/student';
import { ExamResult } from '../exam-service/exam.model';
import { ExamService } from '../exam-service/exam.service';

@Component({
    selector: 'app-exam-result',
    templateUrl: './exam-result.component.html',
    styleUrls: ['./exam-result.component.css']
})
export class ExamResultComponent implements OnInit, CommonEntityDialogInterface<ExamResult> {

    examResult: ExamResult;
    title: string;
    examResultFields: any[];
    event: EventEmitter<EntityActionEvent<ExamResult>> = new EventEmitter();
    action = ENTITY_ACTION;

    suggestionStudents: Student[];
    studentData: Student;

    constructor(private examService: ExamService, private route: ActivatedRoute) {
        this.examResultFields = [{ field: 'examDate', header: 'Exam Date' },
        { field: 'speaking', header: 'Speaking' },
        { field: 'listening', header: 'Listening' },
        { field: 'reading', header: 'Reading', },
        { field: 'writing', header: 'Writing', },
        { field: 'overall', header: 'Overall', },
        { field: 'result', header: 'Result', }]
    }
    getEvent(): EventEmitter<EntityActionEvent<ExamResult>> {
        return this.event;
    }

    setEntityDialogData(title: string, isNewEntity: boolean, entity: any): void {
        this.title = title;
        this.examResult = entity;
    }

    ngOnInit(): void {

    }

    updateExamResult(action: ENTITY_ACTION) {
        this.event.emit({ action, entity: this.examResult });
    }

}
