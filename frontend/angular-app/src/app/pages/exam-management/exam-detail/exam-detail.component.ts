import { Component, EventEmitter, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ENTITY_ACTION } from 'src/app/shared/app-constant.service';
import { CommonEntityDialogInterface, EntityActionEvent } from '../../../shared/components/common-detail-dialog/common-entity-dialog-interface';
import { Exam } from '../exam-service/exam.model';
import { ExamService } from '../exam-service/exam.service';

@Component({
    selector: 'app-exam-detail',
    templateUrl: './exam-detail.component.html',
    styleUrls: ['./exam-detail.component.css']
})
export class ExamDetailComponent implements OnInit, CommonEntityDialogInterface<Exam> {

    examInfo: Exam;
    title: string;
    isNewExam: boolean;
    examFields: any[];
    event: EventEmitter<EntityActionEvent<Exam>> = new EventEmitter();
    action = ENTITY_ACTION;

    constructor(private examService: ExamService, private route: ActivatedRoute) {
        this.examFields = [{ field: 'name', header: 'Name', cols: 2, required: 'true' },
        { field: 'examType', header: 'Type', cols: 2 },
        { field: 'description', header: 'Description', cols: 2 }]
    }

    setEntityDialogData(title: string, isNewEntity: boolean, entity: any): void {
        this.title = title;
        this.isNewExam = isNewEntity;
        this.examInfo = entity;
    }

    ngOnInit(): void {
        const id = this.route.snapshot.paramMap.get('id');
        if (id) {
            this.examService.getExamById(+id).subscribe((exam: Exam) => {
                this.examInfo = exam;
            });
            this.isNewExam = false;
        }
    }

    sendEvent(action: ENTITY_ACTION) {
        switch (action) {
            case ENTITY_ACTION.CREATE:
                this.examService.createExam(this.examInfo).subscribe();
                break;
            case ENTITY_ACTION.EDIT:
                this.examService.updateExam(this.examInfo).subscribe();
                break;
            case ENTITY_ACTION.DELETE:
                this.examService.deleteExam(this.examInfo).subscribe();
                break;
        }
        this.event.emit({ action, entity: this.examInfo });
    }

    getEvent(): EventEmitter<EntityActionEvent<Exam>> {
        return this.event;
    }
}
